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
import yuriy.spring.library_service.dto.book.BookCreateDto;
import yuriy.spring.library_service.dto.book.BookFilterDto;
import yuriy.spring.library_service.dto.book.BookResponseDto;
import yuriy.spring.library_service.mapper.BookMapper;
import yuriy.spring.library_service.service.BookService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BooksController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(BookFilterDto filter) {
        return ResponseEntity
                .ok(bookMapper.toListDto(bookService.getBooks(filter)));
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookCreateDto bookDto,
                                                      BindingResult bindingResult,
                                                      UriComponentsBuilder uriBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        }
        var book = bookService.save(bookDto);
        return ResponseEntity.created(uriBuilder
                .replacePath("/api/v1/books/{id}")
                .build(Map.of("id", book.getId())))
                .body(bookMapper.toDto(book));
    }
}
