package com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Length;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Min;

public class MinValidator implements AnnotationValidator<Min> {
    @Override
    public boolean validate(Object value, Min annotation) {
        Number number;
        if(value instanceof Number) {
            number = (Number) value;
            return number.doubleValue() >= annotation.value();
        }
        return false;
    }

    @Override
    public String getMessage(String fieldName, Min annotation) {
        String message = annotation.message();
        message = message.replace("{field}", fieldName);
        message = message.replace("{value}", Long.toString(annotation.value()));
        return message;
    }
}
