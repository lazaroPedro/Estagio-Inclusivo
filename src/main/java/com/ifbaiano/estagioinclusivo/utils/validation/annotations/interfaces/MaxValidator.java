package com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Max;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Min;

public class MaxValidator implements AnnotationValidator<Max> {
    @Override
    public boolean validate(Object value, Max annotation) {
        Number number;
        if(value instanceof Number) {
            number = (Number) value;
            return number.doubleValue() <= annotation.value();
        }
        return false;
    }

    @Override
    public String getMessage(String fieldName, Max annotation) {
        String message = annotation.message();
        message = message.replace("{field}", fieldName);
        message = message.replace("{value}", Long.toString(annotation.value()));
        return message;
    }
}
