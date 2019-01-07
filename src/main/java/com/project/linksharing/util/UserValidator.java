package com.project.linksharing.util;

import com.project.linksharing.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == User.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace (errors,"username","invalid.name"  );
        ValidationUtils.rejectIfEmptyOrWhitespace (errors,"password","invalid.password"  );
        ValidationUtils.rejectIfEmptyOrWhitespace (errors,"email","invalid.email"  );

    }
}
