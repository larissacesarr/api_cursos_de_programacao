package br.com.larissacesar.cursos_programacao.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CourseStatus {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

    CourseStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static CourseStatus fromDescricao(String value) {
        for (CourseStatus status : CourseStatus.values()) {
            if (status.descricao.equalsIgnoreCase(value) || status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + value);
    }
}