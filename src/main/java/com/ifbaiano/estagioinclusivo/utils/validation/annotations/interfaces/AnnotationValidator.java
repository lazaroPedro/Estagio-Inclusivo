package com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces;

import java.lang.annotation.Annotation;

public interface AnnotationValidator<A extends Annotation> {
    boolean validate(Object value, A annotation);

    String getMessage(String fieldName, A annotation);
}
