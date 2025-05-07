package com.ifbaiano.estagioinclusivo.utils.validation;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces.AnnotationValidator;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces.NotBlankValidator;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces.NotNullValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ValidatorAnnot {
    private static final Map<Class<? extends Annotation>, AnnotationValidator<? extends Annotation>> mapValidator = new HashMap<>();
    static {
    mapValidator.put(NotNull.class, new NotNullValidator());
    mapValidator.put(NotBlank.class, new NotBlankValidator());


}


    private ValidatorAnnot() {}
    public static void validar(Object o) throws ValidationException, IllegalAccessException {
        Class<?> clazz = o.getClass();
        ListErrors errors = new ListErrors();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            for (Field f : current.getDeclaredFields()) {
                f.setAccessible(true);
                Object value = f.get(o);
                Annotation[] annotations = f.getDeclaredAnnotations();
                for (Annotation annot : annotations) {
                    AnnotationValidator validator =  mapValidator.get(annot.getClass());
                    if (validator != null) {
                        if (!validator.validate(f)){
                            errors.add(new ErroCampo(f.getName(), f,
                                    validator.getMessage(f.getName(), annot), clazz.getSimpleName() ));
                        }
                    }
                }



            }
        current = current.getSuperclass();
        }
    if (!errors.isEmpty()) {
        throw new ValidationException(o.getClass().getSimpleName(), errors);
    }
    }}