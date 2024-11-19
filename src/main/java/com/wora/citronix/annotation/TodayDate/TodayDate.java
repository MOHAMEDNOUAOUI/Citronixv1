package com.wora.citronix.annotation.TodayDate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.PARAMETER , ElementType.FIELD , ElementType.METHOD , ElementType.ANNOTATION_TYPE , ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TodayDateValidator.class)
public @interface TodayDate {
    String message() default "Date must be today's date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
