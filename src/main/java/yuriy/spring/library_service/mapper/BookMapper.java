package yuriy.spring.library_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import yuriy.spring.library_service.dto.book.BookCreateDto;
import yuriy.spring.library_service.dto.book.BookResponseDto;
import yuriy.spring.library_service.entity.Book;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookCreateDto dto);

    @Mapping(target = "authors",
            expression = "java(book.getAuthors().stream().map(a -> a.getName()).toList())")
    BookResponseDto toDto(Book book);

    List<BookResponseDto> toListDto(List<Book> entityList);
}
