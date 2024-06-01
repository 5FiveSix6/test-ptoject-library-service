package yuriy.spring.library_service.exception;

public class BookAuthorsAlreadyExistException extends RuntimeException {

    public BookAuthorsAlreadyExistException(String message) {
        super(message);
    }
}
