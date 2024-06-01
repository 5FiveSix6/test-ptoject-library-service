package yuriy.spring.library_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yuriy.spring.library_service.dto.author.AuthorCreateDto;
import yuriy.spring.library_service.dto.author.AuthorUpdateDto;
import yuriy.spring.library_service.entity.Author;
import yuriy.spring.library_service.exception.AuthorAlreadyExistException;
import yuriy.spring.library_service.exception.AuthorNotFoundException;
import yuriy.spring.library_service.mapper.AuthorMapper;
import yuriy.spring.library_service.updater.AuthorUpdater;
import yuriy.spring.library_service.repository.AuthorRepository;
import yuriy.spring.library_service.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuthorUpdater authorUpdater;

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author save(AuthorCreateDto authorDto) {
        var author = authorMapper.toEntity(authorDto);
        try {
            return authorRepository.save(author);
        } catch (DataIntegrityViolationException e) {
            throw new AuthorAlreadyExistException("errors.author.already_exist");
        }
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("errors.author.not_found"));
    }

    @Override
    @Transactional
    public Author update(AuthorUpdateDto authorDto) {
        try {
            var author = authorRepository.findById(authorDto.getId())
                    .orElseThrow(() -> new AuthorNotFoundException("errors.author.not_found"));
            authorUpdater.update(authorDto, author);
            return author;
        } catch (DataIntegrityViolationException e) {
            throw new AuthorAlreadyExistException("errors.author.already_exist");
        }
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getByName(String name) {
        return authorRepository.findByName(name)
                .orElseThrow(() -> new AuthorNotFoundException("errors.author.not_found"));
    }
}
