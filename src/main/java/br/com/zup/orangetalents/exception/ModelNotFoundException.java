package br.com.zup.orangetalents.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Modelo não encontrado.")
public class ModelNotFoundException extends Exception {
    public ModelNotFoundException() {
        super();
    }
}
