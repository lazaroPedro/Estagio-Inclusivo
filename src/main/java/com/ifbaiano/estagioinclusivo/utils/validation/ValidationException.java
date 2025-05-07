package com.ifbaiano.estagioinclusivo.utils.validation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationException extends Exception {
    private final Map<String, ListErrors> errors;

    public ValidationException(String field, ListErrors errors) {
        super("Validação Falhou. Total de Campos Incorretos: " + errors.size());
        this.errors = new HashMap<>();
        add(field , errors);
    }
    public ListErrors getErrors(){
        if(!errors.isEmpty()){
                return errors.values().iterator().next();
        }
        return null;
    }
    public ListErrors getErrors(String field) {
        return errors.get(field);
    }
    public void add(String classe, ListErrors error) {
        errors.put(classe, error);
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
