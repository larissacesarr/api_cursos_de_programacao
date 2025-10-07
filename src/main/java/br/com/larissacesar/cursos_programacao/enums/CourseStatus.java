package br.com.larissacesar.cursos_programacao.enums;

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
}