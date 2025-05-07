package com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;

public class NotNullValidator implements AnnotationValidator<NotNull> {
    @Override
    public boolean validate(Object annotation) {
        return annotation != null;
    }

    @Override
    public String getMessage(String fieldName, NotNull annotation) {
        return annotation.message().replace("{field}", fieldName);
    }
}
