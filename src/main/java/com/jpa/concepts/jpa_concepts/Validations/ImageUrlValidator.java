package com.jpa.concepts.jpa_concepts.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageUrlValidator implements ConstraintValidator<ValidImageUrl,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("Validating image url");

        return s.toLowerCase().endsWith(".png") || s.toLowerCase().endsWith(".jpg");
    }
}
