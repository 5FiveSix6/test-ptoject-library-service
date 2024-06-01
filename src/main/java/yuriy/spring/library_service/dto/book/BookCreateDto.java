package yuriy.spring.library_service.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record BookCreateDto(

        @NotBlank(message = "{errors.book.title_is_blank}")
        @Size(min = 6, max = 255, message = "{errors.book.invalid_title_size}")
        String title,

        @NotBlank(message = "{errors.book.isbn_is_blank}")
        @Size(min = 13, max = 13, message = "{errors.book.invalid_isbn_size}")
        String isbn,

        @NotNull(message = "{errors.book.date_is_null}")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "{errors.book.invalid_date_format}")
        String createdAt) {
}
