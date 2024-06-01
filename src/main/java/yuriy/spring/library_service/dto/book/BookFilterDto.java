package yuriy.spring.library_service.dto.book;

public record BookFilterDto(String title,
                            String isbn,
                            String authorName) {
}
