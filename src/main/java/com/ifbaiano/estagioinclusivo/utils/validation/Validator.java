package com.ifbaiano.estagioinclusivo.utils.validation;

import com.ifbaiano.estagioinclusivo.model.Candidato;
import com.ifbaiano.estagioinclusivo.model.Curso;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    public static void validar(Object o) throws ValidationException, IllegalAccessException {
        Class<?> clazz = o.getClass();
        List<ErroCampo> erroCampos = new ArrayList<>();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                Object value =  f.get(o);
                if(f.isAnnotationPresent(NotNull.class)) {
                    String message = f.getAnnotation(NotNull.class).message();
                    message = message.replace("{field}", f.getName());
                    notNull(value, f.getName(), message, erroCampos);
                }
                if(f.isAnnotationPresent(NotBlank.class)){
                    String message = f.getAnnotation(NotBlank.class).message();
                    message = message.replace("{field}", f.getName());
                    notBlank((String) value, f.getName(), message, erroCampos);
                }
                if (f.isAnnotationPresent(Max.class)){
                    String message = f.getAnnotation(Max.class).message();
                    message = message.replace("{field}", f.getName());
                    message = message.replace("{max}",Long.toString(f.getAnnotation(Max.class).value()));
                    maxNumber((Number) value, f.getAnnotation(Max.class).value(), f.getName(), message, erroCampos);
                }
                if (f.isAnnotationPresent(Min.class)){
                    String message = f.getAnnotation(Min.class).message();
                    message = message.replace("{field}", f.getName());
                    message = message.replace("{min}",Long.toString(f.getAnnotation(Min.class).value()));
                    minNumber((Number) value, f.getAnnotation(Min.class).value(), f.getName(), message, erroCampos);
                }
                if(f.isAnnotationPresent(Positive.class)){
                    String message = f.getAnnotation(Positive.class).message();
                    message = message.replace("{field}", f.getName());

                    positive((Number) value, f.getName(), message, erroCampos);
                }
                if(f.isAnnotationPresent(Negative.class)){
                    String message = f.getAnnotation(Negative.class).message();
                    message = message.replace("{field}", f.getName());

                    negative((Number) value,f.getName(), message, erroCampos);
                }

            }
            current = current.getSuperclass();


        }      if(!erroCampos.isEmpty()){
            throw new ValidationException(erroCampos);
        }
    }

    public void validarNotNull(List<ErroCampo> erroCampos) throws ValidationException, IllegalAccessException {}


    public static boolean notNull(Object valor, String nomeCampo, String mensagemErro, List<ErroCampo> erros) {
        if (valor == null) {
            erros.add(new ErroCampo(nomeCampo, "null", mensagemErro));
            return false;
        }
        return true;
    }


    public static boolean notBlank(String valor, String nomeCampo, String messagem , List<ErroCampo> erros) {
        if(notNull(valor, nomeCampo, messagem, erros)){
            if(valor.trim().isEmpty()){
                erros.add(new ErroCampo(nomeCampo, valor, messagem));
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean maxNumber(Number number, Number max, String nomeCampo,String messagem, List<ErroCampo> erros) {
        if (notNull(number,nomeCampo,messagem,erros)) {
            if(number.doubleValue() > max.doubleValue()){
                erros.add(new ErroCampo(nomeCampo,number, messagem));
                return false;

            }
            return true;
        }
        return false;
    }

    public static boolean minNumber(Number number, Number min, String nomeCampo, String messagem,  List<ErroCampo> erros) {
        if (notNull(number,nomeCampo,messagem, erros)) {
            if(number.doubleValue() < min.doubleValue()){
                erros.add(new ErroCampo(nomeCampo,number, messagem));
                return false;
            }
            return true;
        }
        return false;
    }
    public static boolean positive(Number number, String nomeCampo,String messagem, List<ErroCampo> erros) {
        if (notNull(number,nomeCampo,messagem,erros)) {
            if(number.doubleValue() <= 0){
                erros.add(new ErroCampo(nomeCampo,number, messagem));
                return false;

            }
            return true;
        }
        return false;
    }
    public static boolean negative(Number number, String nomeCampo,String messagem, List<ErroCampo> erros) {
        if (notNull(number,nomeCampo,messagem,erros)) {
            if(number.doubleValue() >= 0){
                erros.add(new ErroCampo(nomeCampo,number, messagem));
                return false;

            }
            return true;
        }
        return false;
    }

}
