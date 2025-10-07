package br.com.larissacesar.cursos_programacao.repository;

import br.com.larissacesar.cursos_programacao.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID > {
    List<CourseEntity> findByNameContainingOrCategoryIgnoreCase(String name, String category);
    Optional<CourseEntity> findById(UUID uuid);
}