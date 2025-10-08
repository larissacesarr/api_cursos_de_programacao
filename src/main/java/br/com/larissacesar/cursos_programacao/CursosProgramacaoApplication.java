package br.com.larissacesar.cursos_programacao;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "API de Cursos de Programação",
                version = "1.0",
                description = "API para gerenciar cursos de programação, incluindo criação, atualização, exclusão e consulta de cursos."))
public class CursosProgramacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosProgramacaoApplication.class, args);
	}

}
