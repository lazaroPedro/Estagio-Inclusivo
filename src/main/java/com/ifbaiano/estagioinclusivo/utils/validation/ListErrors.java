package com.ifbaiano.estagioinclusivo.utils.validation;

import java.util.*;

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

    public Optional<ErroCampo> findField(String field) {

        return erroCampos.stream().filter(erro -> erro.getNomeCampo().equalsIgnoreCase(field)).findFirst();

    }
    public Optional<ErroCampo> findFieldbyClass(String field, String className) {
        return erroCampos.stream().filter(erro -> erro.getNomeCampo().equalsIgnoreCase(field)
                && erro.getClasse().equalsIgnoreCase(className)).findFirst();

    }
    public String findMessageByField(String field) {
        Optional<ErroCampo> erro = findField(field);
        String message = "";
        if(erro.isPresent()){
            message = erro.get().getNomeCampo();

        }
        return message;
    }

}
