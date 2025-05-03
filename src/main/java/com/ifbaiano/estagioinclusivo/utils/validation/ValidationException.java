package com.ifbaiano.estagioinclusivo.utils.validation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<ErroCampo> erroCampos;

    public ValidationException(List<ErroCampo> erroCampos) {
        super("Validação Falhou. Total de Campos Incorretos: " + erroCampos.size());
        this.erroCampos = erroCampos;

    }

    public List<ErroCampo> getErroCampos() {
        return erroCampos;
    }

    public String getMessage() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(erroCampos);
    }
}
