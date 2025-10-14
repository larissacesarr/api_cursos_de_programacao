package br.com.larissacesar.cursos_programacao.controllers;

import br.com.larissacesar.cursos_programacao.entity.CourseEntity;
import br.com.larissacesar.cursos_programacao.enums.CourseStatus;
import br.com.larissacesar.cursos_programacao.repository.CourseRepository;
import br.com.larissacesar.cursos_programacao.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cursos", description = "Gerenciamento de cursos de programação")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    @Operation(summary = "Cadastro de cursos", description = "Cadastrar um novo curso de programação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso ao criar o curso",
                    content = @Content(schema = @Schema(implementation = CourseEntity.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao criar o novo curso")
    })
    public ResponseEntity<CourseEntity> createCourse(@Valid @RequestBody CourseEntity courseEntity) {
        CourseEntity createdCourse = courseService.createCourse(courseEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @GetMapping
    @Operation(summary = "Listar cursos", description = "Listar todos os cursos de programação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso listar os curso",
                    content = @Content(schema = @Schema(implementation = CourseEntity.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao listar os cursos")
    })
    public ResponseEntity<List<CourseEntity>> listCourses() {
        List<CourseEntity> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search")
    @Operation(summary = "Listar cursos", description = "Listar cursos por nome ou categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso ao listar o curso",
                    content = @Content(schema = @Schema(implementation = CourseEntity.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao listar o curso")
    })
    public ResponseEntity<List<CourseEntity>> searchCourses(@RequestParam(required = false) String name, @RequestParam(required = false) String category) {
        List<CourseEntity> result = courseService.searchCourses(name, category);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar curso", description = "Atualizar as informações de um curso de programação existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o curso",
                    content = @Content(schema = @Schema(implementation = CourseEntity.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o curso")
    })
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable UUID id, @Valid @RequestBody CourseEntity courseEntity) {
        CourseEntity updatedCourse = courseService.updateCourse(id, courseEntity);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar curso", description = "Deletar um curso de programação existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso ao deletar o curso",
                    content = @Content(schema = @Schema(implementation = CourseEntity.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao deletar o curso")
    })
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/active")
    @Operation(summary = "Atualizar status", description = "Ativar ou desativar um curso de programação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar o status do curso",
                    content = @Content(schema = @Schema(implementation = CourseEntity.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao atualizar o status do curso")
    })
    public ResponseEntity<CourseEntity> toggleActive(@PathVariable UUID id, @RequestBody CourseStatus status) {
        CourseEntity updatedCourse = courseService.toggleActiveStatus(id);
        return ResponseEntity.ok(updatedCourse);
    }
}
