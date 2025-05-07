package com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

public class NotBlankValidator implements AnnotationValidator<NotNull> {
    @Override
    public boolean validate(Object annotation) {


        return annotation.toString().trim().isEmpty();
    }

    @Override
    public String getMessage(String fieldName, NotNull annotation) {
        return annotation.message().replace("{field}", fieldName);
    }
}
