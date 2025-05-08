package com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Length;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.Pattern;

public class PatternValidator implements AnnotationValidator<Pattern> {
    @Override
    public boolean validate(Object value, Pattern annotation) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(annotation.regex());
        return pattern.matcher(value.toString()).matches();
    }

    @Override
    public String getMessage(String fieldName, Pattern annotation) {
        String message = annotation.message();
        message = message.replace("{field}", fieldName);
        return message;
    }
}
