package com.example.kabisa_backend.validation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ValidId.IdValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidId {

    String message() default "Parameter Id can't be larger than 1000.";

    Class[] groups() default {};

    Class[] payload() default {};

    class IdValidator implements ConstraintValidator<ValidId, Integer>{

        private static final Integer MAX_SIZE = 1000;

        @Override
        public boolean isValid(Integer id, ConstraintValidatorContext cxt){
            return id < MAX_SIZE;
        }
    }
}
