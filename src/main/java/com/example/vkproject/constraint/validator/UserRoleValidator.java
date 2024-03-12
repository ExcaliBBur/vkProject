package com.example.vkproject.constraint.validator;


import com.example.vkproject.constraint.UserRoleConstraint;
import com.example.vkproject.model.entity.jpa.enumiration.UserRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class UserRoleValidator implements ConstraintValidator<UserRoleConstraint, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(s)) {
            return true;
        }

        try {
            UserRole role = UserRole.valueOf(s);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
