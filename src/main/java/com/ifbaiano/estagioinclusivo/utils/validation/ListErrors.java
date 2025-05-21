package com.ifbaiano.estagioinclusivo.utils.validation;

import java.util.*;
import java.util.stream.Collectors;

public class ListErrors {
    private List<ErroCampo> erroCampos;

    public ListErrors() {
        this.erroCampos = new ArrayList<>();
    }

    public void add(ErroCampo field) {
        erroCampos.add(field);

    }

    public List<ErroCampo> getErroCampos() {
        return erroCampos;
    }

    public int size(){
         return erroCampos.size();
    }
    public boolean isEmpty(){
        return erroCampos.isEmpty();
    }

    public List<ErroCampo> findField(String field) {
        return erroCampos.stream().filter(erro -> erro.getNomeCampo().equalsIgnoreCase(field)).collect(Collectors.toList());
    }

    public String findMessage(String field) {
        List<ErroCampo> erro = findField(field);
        StringBuilder message = new StringBuilder();
        if(!erro.isEmpty()){
            erro.forEach(erroCampo -> {
                message.append(erroCampo.getNomeCampo()).append("\n");
            });

        }
        return message.toString();
    }

}
