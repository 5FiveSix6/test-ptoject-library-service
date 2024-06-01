package yuriy.spring.library_service.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateDto {

    private Long id;

    @NotBlank(message = "{errors.author.name_is_blank}")
    @Size(min = 6, max = 255, message = "{errors.author.invalid_name_size}")
    private String name;
}
