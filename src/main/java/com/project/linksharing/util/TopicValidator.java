package com.project.linksharing.util;

import com.project.linksharing.model.Topic;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class TopicValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Topic.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace (errors,"name","invalid.name"  );
        ValidationUtils.rejectIfEmptyOrWhitespace (errors,"visibility","invalid.visibility"  );
    }
}
