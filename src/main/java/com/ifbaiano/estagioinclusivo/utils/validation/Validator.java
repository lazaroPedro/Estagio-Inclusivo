package com.ifbaiano.estagioinclusivo.utils.validation;

import java.util.List;
import java.util.regex.Pattern;

public class Validator {
    public static boolean notNull(Object valor, String nomeCampo, List<ErroCampo> erros) {
        return notNull(valor, nomeCampo, "O campo " + nomeCampo + "não pode ser nulo", erros);
    }

    public static boolean notNull(Object valor, String nomeCampo, String mensagemErro, List<ErroCampo> erros) {
        if (valor == null) {
            erros.add(new ErroCampo(nomeCampo, null, mensagemErro));
            return false;
        }
        return true;
    }


    public static boolean notEmpty(String valor, String nomeCampo, List<ErroCampo> erros) {
        if(notNull(valor, nomeCampo, erros)){
            if(valor.trim().isEmpty()){
                erros.add(new ErroCampo(nomeCampo, valor, "O campo " + nomeCampo + " não pode ser vazio"));
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean maxNumber(Number number, Number max, String nomeCampo, List<ErroCampo> erros) {
        if (notNull(number,nomeCampo,erros)) {
            if(number.doubleValue() > max.doubleValue()){
                erros.add(new ErroCampo(nomeCampo,number, "O campo " + nomeCampo + " não pode ser vazio"));
                return false;

            }
            return true;
        }
        return false;
    }

    public static boolean minNumber(Number number, Number min, String nomeCampo, List<ErroCampo> erros) {
        if (notNull(number,nomeCampo,erros)) {
            if(number.doubleValue() < min.doubleValue()){
                erros.add(new ErroCampo(nomeCampo,number, "O campo " + nomeCampo + " não pode ser vazio"));
                return false;
            }
            return true;
        }
        return false;
    }

    public static void isPositivo(Number valor, String nomeCampo, List<ErroCampo> erros) {
        minNumber(valor, 0, nomeCampo, erros);
    }
    public static void isNegativo(Number valor, String nomeCampo, List<ErroCampo> erros) {
        maxNumber(valor, 0, nomeCampo, erros);
    }

    private static boolean isValidCPF(String cpf){
        return true;
    }
}
