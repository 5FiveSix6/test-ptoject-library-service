package yuriy.spring.library_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yuriy.spring.library_service.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String name);
}
