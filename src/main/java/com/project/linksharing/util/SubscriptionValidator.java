package com.project.linksharing.util;

import com.project.linksharing.model.Subscription;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SubscriptionValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Subscription.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace (errors,"topic.id","invalid.topicId" );
        ValidationUtils.rejectIfEmptyOrWhitespace (errors,"seriousness","invalid.seriousness" );

    }
}
