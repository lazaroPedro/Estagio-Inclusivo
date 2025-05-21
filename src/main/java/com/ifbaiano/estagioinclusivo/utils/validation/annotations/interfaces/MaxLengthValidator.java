package com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Length;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.MaxLength;

public class MaxLengthValidator implements AnnotationValidator<MaxLength> {
    @Override
    public boolean validate(Object value, MaxLength annotation) {
        return value.toString().trim().length() <= annotation.value() ;
    }

    @Override
    public String getMessage(String fieldName, MaxLength annotation) {
        String message = annotation.message();
        message = message.replace("{field}", fieldName);
        message = message.replace("{value}", Long.toString(annotation.value()));
        return message;
    }
}
