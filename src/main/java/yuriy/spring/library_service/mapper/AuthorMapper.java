package yuriy.spring.library_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import yuriy.spring.library_service.dto.author.AuthorCreateDto;
import yuriy.spring.library_service.dto.author.AuthorResponseDto;
import yuriy.spring.library_service.entity.Author;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author toEntity(AuthorCreateDto dto);

    @Mapping(target = "books",
            expression = "java(author.getBooks().stream().map(b -> b.getTitle()).toList())")
    AuthorResponseDto toDto(Author author);

    List<AuthorResponseDto> toListDto(List<Author> authors);
}
