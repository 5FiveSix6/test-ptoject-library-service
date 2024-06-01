package yuriy.spring.library_service.updater;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import yuriy.spring.library_service.dto.book.BookUpdateDto;
import yuriy.spring.library_service.entity.Book;

import java.time.LocalDate;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookUpdater {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "authors", ignore = true)})
    void update(BookUpdateDto dto, @MappingTarget Book book);
}
