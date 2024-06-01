package yuriy.spring.library_service.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import yuriy.spring.library_service.dto.book.BookFilterDto;
import yuriy.spring.library_service.entity.Book;

@Component
public class SpecificationConfigurer {

    public Specification<Book> cofigureBookSpecification(BookFilterDto filter) {
        if (filter == null) {
            return null;
        }
        Specification<Book> spec = Specification.where(null);
        if (filter.title() != null) {
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(root.get("title"), "%".concat(filter.title()).concat("%")),
                    cb.equal(root.get("title"), filter.title()),
                    cb.like(root.get("title"), filter.title().concat("%")),
                    cb.like(root.get("title"), "%".concat(filter.title()))));
        }
        if (filter.isbn() != null) {
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(root.get("isbn"), "%".concat(filter.isbn()).concat("%")),
                    cb.equal(root.get("isbn"), filter.isbn()),
                    cb.like(root.get("isbn"), filter.isbn().concat("%")),
                    cb.like(root.get("isbn"), "%".concat(filter.isbn()))));
        }
        if (filter.authorName() != null) {
            spec = spec.and((root, query, cb) -> cb.or(
                    cb.like(root.get("authors").get("name"), "%".concat(filter.authorName()).concat("%")),
                    cb.equal(root.get("authors").get("name"), filter.authorName()),
                    cb.like(root.get("authors").get("name"), filter.authorName().concat("%")),
                    cb.like(root.get("authors").get("name"), "%".concat(filter.authorName()))));
        }
        return spec;
    }
}
