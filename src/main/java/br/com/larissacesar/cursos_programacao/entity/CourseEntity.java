package br.com.larissacesar.cursos_programacao.entity;

import br.com.larissacesar.cursos_programacao.enums.CourseStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID id;

    @NotBlank(message = "Esse campo é obrigatório")
    @Schema(example = "Java para Iniciantes", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "Esse campo é obrigatório")
    @Schema(example = "Curso introdutório de Java", requiredMode = Schema.RequiredMode.REQUIRED)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private CourseStatus status = CourseStatus.ATIVO;

    @CreationTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;
}
