package com.ifbaiano.estagioinclusivo.utils.validation;

import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotBlank;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.NotNull;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces.AnnotationValidator;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces.NotBlankValidator;
import com.ifbaiano.estagioinclusivo.utils.validation.annotations.interfaces.NotNullValidator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorAnnot {
    private static final Map<Class<? extends Annotation>, AnnotationValidator<? extends Annotation>> mapValidator = new HashMap<>();
    static {
    mapValidator.put(NotNull.class, new NotNullValidator());
    mapValidator.put(NotBlank.class, new NotBlankValidator());


}


    private ValidatorAnnot() {}

    public static void validate(Object o) throws ValidationException {
        Class<?> clazz = o.getClass();
        ListErrors errors = new ListErrors();
        Class<?> current = clazz;
        while (current != null && current != Object.class) {
            for (Field f : current.getDeclaredFields()) {
                f.setAccessible(true);
                Object value = null;
                try {
                    value = f.get(o);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                Annotation[] annotations = f.getAnnotations();
                if(Arrays.stream(annotations).noneMatch(a -> a.annotationType().equals(NotNull.class))) {
                    if (value == null) {
                        errors.add(new ErroCampo(f.getName(), "null", "O campo " + f.getName() + " não pode ser nulo"));
                        continue;
                    }
                }


                for (Annotation annot : annotations) {

                    @SuppressWarnings("unchecked")
                    AnnotationValidator<Annotation> validator = (AnnotationValidator<Annotation>) mapValidator.get(annot.getClass());
                    if (validator != null) {
                        if (!validator.validate(value)){
                            errors.add(new ErroCampo(f.getName(), f,
                                    validator.getMessage(f.getName(), annot)));
                        }
                    }
                }



            }
        current = current.getSuperclass();
        }
    if (!errors.isEmpty()) {
        throw new ValidationException(errors);
    }
    }
    public static void validate(List<Object> objects) throws ValidationException, ValidationMapException {
        Map<String, ListErrors> errors = new HashMap<>();

        for (Object o : objects) {
            try {
                validate(o);
            } catch (ValidationException e) {
                errors.put(o.getClass().getSimpleName(), e.getErrors());
            }

        }
        if (!errors.isEmpty()) {
            throw new ValidationMapException(errors);
        }

    }

}