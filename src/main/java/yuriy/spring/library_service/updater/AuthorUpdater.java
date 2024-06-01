package yuriy.spring.library_service.updater;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import yuriy.spring.library_service.dto.author.AuthorUpdateDto;
import yuriy.spring.library_service.entity.Author;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorUpdater {

    @Mapping(target = "id", ignore = true)
    void update(AuthorUpdateDto dto, @MappingTarget Author author);
}
