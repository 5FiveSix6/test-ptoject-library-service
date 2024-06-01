package yuriy.spring.library_service.dto.author;

import java.util.List;

public record AuthorResponseDto(Long id,
                                String name,
                                List<String> books) {
}
