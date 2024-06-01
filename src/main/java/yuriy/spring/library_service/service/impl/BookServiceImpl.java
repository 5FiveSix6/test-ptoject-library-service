package yuriy.spring.library_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yuriy.spring.library_service.dto.book.BookCreateDto;
import yuriy.spring.library_service.dto.book.BookFilterDto;
import yuriy.spring.library_service.dto.book.BookUpdateDto;
import yuriy.spring.library_service.entity.Book;
import yuriy.spring.library_service.exception.BookAlreadyExistException;
import yuriy.spring.library_service.exception.BookAuthorsAlreadyExistException;
import yuriy.spring.library_service.exception.BookNotFoundException;
import yuriy.spring.library_service.mapper.BookMapper;
import yuriy.spring.library_service.repository.BookRepository;
import yuriy.spring.library_service.service.AuthorService;
import yuriy.spring.library_service.service.BookService;
import yuriy.spring.library_service.specification.SpecificationConfigurer;
import yuriy.spring.library_service.updater.BookUpdater;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorService authorService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookUpdater bookUpdater;
    private final SpecificationConfigurer specificationConfigurer;

    @Override
    public List<Book> getBooks(BookFilterDto filter) {
        return bookRepository.findAll(specificationConfigurer.cofigureBookSpecification(filter));
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("errors.book.not_found"));
    }

    @Override
    public Book save(BookCreateDto bookDto) {
        try {
            return bookRepository.save(bookMapper.toEntity(bookDto));
        } catch (DataIntegrityViolationException e) {
            throw new BookAlreadyExistException("errors.book.already_exist");
        }
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book update(BookUpdateDto bookDto) {
        try {
            var book = bookRepository.findById(bookDto.getId())
                    .orElseThrow(() -> new BookNotFoundException("errors.book.not_found"));
            bookUpdater.update(bookDto, book);
            if (bookDto.getAuthors() != null && !bookDto.getAuthors().isEmpty()) {
                var authors = bookDto.getAuthors()
                        .stream()
                        .map(authorService::getByName)
                        .toList();
                book.getAuthors()
                        .stream()
                        .filter(authors::contains)
                        .findFirst()
                        .ifPresent(author -> {
                            throw new BookAuthorsAlreadyExistException("errors.book.author_already_exist");
                        });
                book.getAuthors().addAll(authors);
            }
            return book;
        } catch (BookAuthorsAlreadyExistException e) {
            throw e;
        } catch (DataIntegrityViolationException e) {
            throw new BookAlreadyExistException("errors.book.already_exist");
        }
    }
}
