package com.ifbaiano.estagioinclusivo.utils.validation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationException extends Exception {
    private final ListErrors errors;

    public ValidationException(ListErrors errors) {
        super("Validação Falhou. Total de Campos Incorretos: " + errors.size());
        this.errors = errors;
    }

    public ListErrors getErrors() {
        return errors;
    }

    public String getMessage() {
        StringBuilder mensagemCompleta = new StringBuilder("Erros de validação:\n");
            for (ErroCampo erro : getErrors().getErroCampos()) {
                mensagemCompleta.append(erro.toString()).append("\n");
            }
            return mensagemCompleta.toString();
    }
    public String getJson() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(errors);

    }
}
