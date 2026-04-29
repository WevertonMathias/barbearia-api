package com.barbearia.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Esta anotação diz ao Spring para "ouvir" erros em todo o projeto
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity tratarErroRegraNegocio(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Trata erros de validação do Bean Validation (@NotNull, @Future, etc)
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream()
                .map(DadosErroValidacao::new).toList();
        return ResponseEntity.badRequest().body(erros);
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(org.springframework.validation.FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
