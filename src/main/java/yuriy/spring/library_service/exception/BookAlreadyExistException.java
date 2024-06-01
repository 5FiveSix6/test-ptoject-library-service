package yuriy.spring.library_service.exception;

public class BookAlreadyExistException extends RuntimeException {

    public BookAlreadyExistException(String message) {
        super(message);
    }
}
