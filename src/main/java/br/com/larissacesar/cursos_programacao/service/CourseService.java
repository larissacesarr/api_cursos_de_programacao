package br.com.larissacesar.cursos_programacao.service;

import br.com.larissacesar.cursos_programacao.entity.CourseEntity;
import br.com.larissacesar.cursos_programacao.enums.CourseStatus;
import br.com.larissacesar.cursos_programacao.exception.CourseNotFoundException;
import br.com.larissacesar.cursos_programacao.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> searchCourses(String name, String category) {
        return courseRepository.findByNameContainingOrCategoryIgnoreCase(name, category);
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    public CourseEntity createCourse(@Valid @RequestBody CourseEntity courseEntity) {
        return courseRepository.save(courseEntity);
    }

    public CourseEntity updateCourse(UUID id, CourseEntity courseEntity) {
        CourseEntity existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException ("Curso não encontrado com ID: " + id));
        existingCourse.setName(courseEntity.getName());
        existingCourse.setCategory(courseEntity.getCategory());

        return courseRepository.save(existingCourse);

    }

    public void deleteCourse(UUID id) {
        CourseEntity existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado com ID: " + id));
        courseRepository.delete(existingCourse);
    }

    public CourseEntity toggleActiveStatus(UUID id){
        CourseEntity existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Curso não encontrado com ID: " + id));
        if (existingCourse.getStatus() == CourseStatus.ATIVO){
            existingCourse.setStatus(CourseStatus.INATIVO);
        } else {
            existingCourse.setStatus(CourseStatus.ATIVO);
        }

        return courseRepository.save(existingCourse);
    }
}