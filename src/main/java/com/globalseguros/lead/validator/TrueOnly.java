package com.globalseguros.lead.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TrueOnlyValidator.class)
public @interface TrueOnly {
    String message() default "El campo debe ser verdadero (true)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
