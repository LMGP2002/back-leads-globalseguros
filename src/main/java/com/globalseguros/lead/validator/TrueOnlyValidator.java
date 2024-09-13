package com.globalseguros.lead.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrueOnlyValidator implements ConstraintValidator<TrueOnly, Boolean> {

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        // Si el valor es null o no es true, la validación falla
        return value != null && value;
    }
}

