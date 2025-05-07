package com.ifbaiano.estagioinclusivo.utils.validation;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    private Validator() {}
    public static void validar(Object o) throws ValidationException{
        Class<?> clazz = o.getClass();
        ListErrors errors = new ListErrors();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            for (Field f : current.getDeclaredFields()) {
                f.setAccessible(true);
                Object value = null;
                try {
                    value = f.get(o);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                boolean notNull = f.isAnnotationPresent(NotNull.class);
                boolean notBlank = f.isAnnotationPresent(NotBlank.class);
                boolean min = f.isAnnotationPresent(Min.class);
                boolean max = f.isAnnotationPresent(Max.class);
                boolean positive = f.isAnnotationPresent(Positive.class);
                boolean negative = f.isAnnotationPresent(Negative.class);


                if(notNull || notBlank || min || max || positive || negative) {
                    String message;
                    if(notNull) {
                        message = f.getAnnotation(NotNull.class).message();

                    } else {
                        message = "O campo {field} não pode ser nulo";

                    }
                    message = message.replace("{field}", f.getName());
                    if (!notNull(clazz.getSimpleName(), value, f.getName(), message, errors)) {
                        continue;
                    }

                }

                if(notBlank && value instanceof String) {
                    String message = f.getAnnotation(NotBlank.class).message();
                    message = message.replace("{field}", f.getName());
                    notBlank(clazz.getSimpleName(), (String) value, f.getName(), message, errors);
                }
                if (max && value instanceof Number) {
                    String message = f.getAnnotation(Max.class).message();
                    message = message.replace("{field}", f.getName());
                    message = message.replace("{max}",Long.toString(f.getAnnotation(Max.class).value()));
                    maxNumber(clazz.getSimpleName(), (Number) value, f.getAnnotation(Max.class).value(), f.getName(), message, errors);
                }
                if (min && value instanceof Number){
                    String message = f.getAnnotation(Min.class).message();
                    message = message.replace("{field}", f.getName());
                    message = message.replace("{min}",Long.toString(f.getAnnotation(Min.class).value()));
                    minNumber(clazz.getSimpleName(), (Number) value, f.getAnnotation(Min.class).value(), f.getName(), message, errors);
                }
                if(positive && value instanceof Number){
                    String message = f.getAnnotation(Positive.class).message();
                    message = message.replace("{field}", f.getName());

                    positive(clazz.getSimpleName(), (Number) value, f.getName(), message, errors);
                }
                if(negative && value instanceof Number){
                    String message = f.getAnnotation(Negative.class).message();
                    message = message.replace("{field}", f.getName());

                    negative(clazz.getSimpleName(), (Number) value,f.getName(), message, errors);
                }

            }
            current = current.getSuperclass();


        }      if(!errors.isEmpty()){
            throw new ValidationException(o.getClass().getSimpleName(),errors);
        }
    }



    public static boolean notNull(String classe , Object valor, String nomeCampo, String mensagemErro, ListErrors erroCampos) {
        if (valor == null) {
            erroCampos.add(new ErroCampo(nomeCampo, "null", mensagemErro, classe));
            return false;
        }
        return true;
    }


    public static boolean notBlank(String classe, String valor, String nomeCampo, String messagem , ListErrors erros) {
            if(valor.trim().isEmpty()){
                erros.add(new ErroCampo(nomeCampo, valor, messagem, classe));
                return false;
            }
            return true;
    }

    public static boolean maxNumber(String classe, Number number, Number max, String nomeCampo, String messagem, ListErrors erros) {
            if(number.doubleValue() > max.doubleValue()){
                erros.add(new ErroCampo(nomeCampo,number, messagem, classe));
                return false;

            }
            return true;

    }

    public static boolean minNumber(String classe, Number number, Number min, String nomeCampo, String messagem, ListErrors erros) {
            if(number.doubleValue() < min.doubleValue()){
                erros.add(new ErroCampo(nomeCampo,number, messagem, classe));
                return false;
            }
            return true;

    }
    public static boolean positive(String classe, Number number, String nomeCampo, String messagem, ListErrors erros) {
            if(number.doubleValue() <= 0){
                erros.add(new ErroCampo(nomeCampo,number, messagem, classe));
                return false;

            }
            return true;

    }
    public static boolean negative(String classe, Number number, String nomeCampo, String messagem, ListErrors erros) {
            if(number.doubleValue() >= 0){
                erros.add(new ErroCampo(nomeCampo,number, messagem, classe));
                return false;

            }
            return true;

    }


}
