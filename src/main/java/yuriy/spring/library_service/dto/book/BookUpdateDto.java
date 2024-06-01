package yuriy.spring.library_service.dto.book;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookUpdateDto {

    private Long id;

    @Size(min = 6, max = 255, message = "{errors.create.book.invalid_name_size}")
    private String title;

    @Size(min = 6, max = 255, message = "{errors.create.book.invalid_name_size}")
    private String isbn;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "{errors.book.invalid_date_format}")
    private String createdAt;

    private List<String> authors;
}
