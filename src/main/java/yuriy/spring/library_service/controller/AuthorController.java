package yuriy.spring.library_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuriy.spring.library_service.dto.author.AuthorResponseDto;
import yuriy.spring.library_service.dto.author.AuthorUpdateDto;
import yuriy.spring.library_service.exception.EmptyRequestBodyException;
import yuriy.spring.library_service.mapper.AuthorMapper;
import yuriy.spring.library_service.service.AuthorService;

@RestController
@RequestMapping("api/v1/authors/{id:\\d+}")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok(authorMapper.toDto(authorService.getById(id)));
    }

    @PatchMapping
    public ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable("id") Long id,
                                                          @Valid @RequestBody AuthorUpdateDto authorDto,
                                                          BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        }
        if (authorDto == null) {
            throw new EmptyRequestBodyException("errors.empty_body");
        }
        var author = authorService.update(authorDto);
        return ResponseEntity.ok(authorMapper.toDto(author));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
