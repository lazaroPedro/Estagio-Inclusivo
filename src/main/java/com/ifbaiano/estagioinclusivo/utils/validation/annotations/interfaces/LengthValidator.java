package com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Length;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

public class LengthValidator implements AnnotationValidator<Length> {
    @Override
    public boolean validate(Object value, Length annotation) {
        return value.toString().trim().length() >= annotation.value() ;
    }

    @Override
    public String getMessage(String fieldName, Length annotation) {
        String message = annotation.message();
        message = message.replace("{field}", fieldName);
        message = message.replace("{value}", Long.toString(annotation.value()));
        return message;
    }
}
