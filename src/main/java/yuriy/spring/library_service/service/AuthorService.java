package yuriy.spring.library_service.service;

import yuriy.spring.library_service.dto.author.AuthorCreateDto;
import yuriy.spring.library_service.dto.author.AuthorUpdateDto;
import yuriy.spring.library_service.entity.Author;

import java.util.List;

public interface AuthorService {

    void delete(Long id);

    Author save(AuthorCreateDto authorDto);

    Author getById(Long id);

    Author update(AuthorUpdateDto authorDto);

    List<Author> getAll();

    Author getByName(String name);
}
