package yuriy.spring.library_service.exception;

public class AuthorAlreadyExistException extends RuntimeException {

    public AuthorAlreadyExistException(String message) {
        super(message);
    }
}
