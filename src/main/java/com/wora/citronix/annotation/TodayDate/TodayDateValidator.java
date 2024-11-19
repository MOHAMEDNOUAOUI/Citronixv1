package com.wora.citronix.annotation.TodayDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class TodayDateValidator implements ConstraintValidator<TodayDate , LocalDate> {

    @Override
    public void initialize(TodayDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null){
            return false;
        }

        return !localDate.isBefore(LocalDate.now());
    }

}
