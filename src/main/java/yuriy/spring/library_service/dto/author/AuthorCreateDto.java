package yuriy.spring.library_service.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthorCreateDto(
        @NotBlank(message = "{errors.author.name_is_blank}")
        @Size(min = 6, max = 255, message = "{errors.author.invalid_name_size}")
        String name) {
}
