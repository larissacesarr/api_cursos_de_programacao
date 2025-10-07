package br.com.larissacesar.cursos_programacao.controllers;

import br.com.larissacesar.cursos_programacao.dto.CourseStatusDTO;
import br.com.larissacesar.cursos_programacao.entity.CourseEntity;
import br.com.larissacesar.cursos_programacao.repository.CourseRepository;
import br.com.larissacesar.cursos_programacao.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<CourseEntity> createCourse(@Valid @RequestBody CourseEntity courseEntity) {
        CourseEntity createdCourse = courseService.createCourse(courseEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @GetMapping
    public ResponseEntity<List<CourseEntity>> listCourses() {
        List<CourseEntity> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CourseEntity>> searchCourses(@RequestParam(required = false) String name, @RequestParam(required = false) String category) {
        List<CourseEntity> result = courseService.searchCourses(name, category);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable UUID id, @Valid @RequestBody CourseEntity courseEntity) {
        CourseEntity updatedCourse = courseService.updateCourse(id, courseEntity);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<CourseEntity> toggleActive(@PathVariable UUID id){
        CourseEntity updatedCourse = courseService.toggleActiveStatus(id);
        return ResponseEntity.ok(updatedCourse);
    }
}
