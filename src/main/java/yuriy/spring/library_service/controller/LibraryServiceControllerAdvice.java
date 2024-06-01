package yuriy.spring.library_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import yuriy.spring.library_service.exception.AuthorAlreadyExistException;
import yuriy.spring.library_service.exception.AuthorNotFoundException;
import yuriy.spring.library_service.exception.BookAlreadyExistException;
import yuriy.spring.library_service.exception.BookAuthorsAlreadyExistException;
import yuriy.spring.library_service.exception.BookNotFoundException;
import yuriy.spring.library_service.exception.EmptyRequestBodyException;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class LibraryServiceControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException e, Locale locale) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                messageSource.getMessage("errors.400.bad_request", new Object[0], locale));
        problemDetails.setProperty("errors",
                e.getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }

    @ExceptionHandler({
            AuthorNotFoundException.class,
            BookNotFoundException.class})
    public ResponseEntity<ProblemDetail> handleNotFoundException(Exception e, Locale locale) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                messageSource.getMessage(e.getMessage(), new Object[0], locale));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetails);
    }

    @ExceptionHandler({
            AuthorAlreadyExistException.class,
            BookAlreadyExistException.class,
            BookAuthorsAlreadyExistException.class,
            EmptyRequestBodyException.class})
    public ResponseEntity<ProblemDetail> handleAlreadyExistException(Exception e, Locale locale) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                messageSource.getMessage(e.getMessage(), new Object[0], locale));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }
}
