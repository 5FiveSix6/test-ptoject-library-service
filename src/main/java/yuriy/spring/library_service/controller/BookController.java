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
import yuriy.spring.library_service.dto.book.BookResponseDto;
import yuriy.spring.library_service.dto.book.BookUpdateDto;
import yuriy.spring.library_service.exception.EmptyRequestBodyException;
import yuriy.spring.library_service.mapper.BookMapper;
import yuriy.spring.library_service.service.BookService;

@RestController
@RequestMapping("api/v1/books/{id:\\d+}")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<BookResponseDto> getBook(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok(bookMapper.toDto(bookService.getById(id)));
    }

    @PatchMapping
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable("id") Long id,
                                                      @Valid @RequestBody BookUpdateDto bookDto,
                                                      BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        }
        if (bookDto == null) {
            throw new EmptyRequestBodyException("errors.empty_body");
        }
        var book = bookService.update(bookDto);
        return ResponseEntity.ok(bookMapper.toDto(book));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
