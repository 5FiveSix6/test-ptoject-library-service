package yuriy.spring.library_service.dto.book;

import java.time.LocalDate;
import java.util.List;

public record BookResponseDto(Long id,
                              String title,
                              String isbn,
                              LocalDate createdAt,
                              List<String> authors) {
}
