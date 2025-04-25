package com.ifbaiano.estagioinclusivo.utils.validation;

import java.time.LocalDate;
import java.util.List;

public class Validator {
    public static boolean notNull(Object valor, String nomeCampo, List<ErroCampo> erros) {
        return notNull(valor, nomeCampo, "O campo " + nomeCampo + " não pode ser nulo", erros);
    }

    public static boolean notNull(Object valor, String nomeCampo, String mensagemErro, List<ErroCampo> erros) {
        if (valor == null) {
            erros.add(new ErroCampo(nomeCampo, null, mensagemErro));
            return false;
        }
        return true;
    }


    public static boolean notBlank(String valor, String nomeCampo, List<ErroCampo> erros) {
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
                erros.add(new ErroCampo(nomeCampo,number, "O campo " + nomeCampo + " deve ser menor ou igual a " + max ));
                return false;

            }
            return true;
        }
        return false;
    }

    public static boolean minNumber(Number number, Number min, String nomeCampo, List<ErroCampo> erros) {
        if (notNull(number,nomeCampo,erros)) {
            if(number.doubleValue() < min.doubleValue()){
                erros.add(new ErroCampo(nomeCampo,number, "O campo " + nomeCampo + " deve ser maior ou igual a " + min ));
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isPositivo(Number valor, String nomeCampo, List<ErroCampo> erros) {
        return minNumber(valor, 0, nomeCampo, erros);
    }
    public static boolean isNegativo(Number valor, String nomeCampo, List<ErroCampo> erros) {
        return maxNumber(valor, 0, nomeCampo, erros);
    }
    public void checkLocalDateTime(String data, String nomeCampo, List<ErroCampo> erros) {

    }
    public static boolean periodoValido(LocalDate inicio, LocalDate fim, String nomeCampoInicio, String nomeCampoFim, List<ErroCampo> erros) {
    if (notNull(inicio, nomeCampoInicio, erros) && notNull(fim, nomeCampoFim, erros)) {
        if (!inicio.isBefore(fim)) {
            erros.add(new ErroCampo(
                nomeCampoInicio,
                inicio,
                "A data de início deve ser antes da data de fim"
            ));
            return false;
        }
        return true;
    }
    return false;
}
    private static boolean isValidCPF(String cpf){
        return true;
    }
}
