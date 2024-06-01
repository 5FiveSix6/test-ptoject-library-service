package yuriy.spring.library_service.exception;

public class EmptyRequestBodyException extends RuntimeException {

    public EmptyRequestBodyException(String message) {
        super(message);
    }
}
