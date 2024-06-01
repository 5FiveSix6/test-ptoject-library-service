package yuriy.spring.library_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import yuriy.spring.library_service.dto.author.AuthorCreateDto;
import yuriy.spring.library_service.dto.author.AuthorResponseDto;
import yuriy.spring.library_service.mapper.AuthorMapper;
import yuriy.spring.library_service.service.AuthorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/authors")
@RequiredArgsConstructor
public class AuthorsController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> findAll() {
        return ResponseEntity
                .ok(authorMapper.toListDto(authorService.getAll()));
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> create(@Valid @RequestBody AuthorCreateDto authorDto,
                                                    BindingResult bindingResult,
                                                    UriComponentsBuilder uriBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        }
        var author = authorService.save(authorDto);
        return ResponseEntity.created(uriBuilder
                .replacePath("/api/v1/authors/{id}")
                .build(Map.of("id", author.getId())))
                .body(authorMapper.toDto(author));
    }
}
