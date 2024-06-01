package yuriy.spring.library_service.service;

import yuriy.spring.library_service.dto.book.BookCreateDto;
import yuriy.spring.library_service.dto.book.BookFilterDto;
import yuriy.spring.library_service.dto.book.BookUpdateDto;
import yuriy.spring.library_service.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getBooks(BookFilterDto filter);

    Book getById(Long id);

    Book save(BookCreateDto bookDto);

    void delete(Long id);

    Book update(BookUpdateDto bookDto);
}
