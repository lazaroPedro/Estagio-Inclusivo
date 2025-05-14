package com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

import java.lang.annotation.Annotation;

public class NotBlankValidator implements AnnotationValidator<NotBlank> {
    @Override
    public boolean validate(Object value, NotBlank annotation) {

        return !value.toString().trim().isEmpty();
    }

    @Override
    public String getMessage(String fieldName, NotBlank annotation) {
        return annotation.message().replace("{field}", fieldName);
    }
}
