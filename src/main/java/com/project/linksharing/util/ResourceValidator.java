package com.project.linksharing.util;

import com.project.linksharing.model.DocumentResource;
import com.project.linksharing.model.LinkResource;
import com.project.linksharing.model.Resource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ResourceValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Resource.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (target instanceof LinkResource){
            ValidationUtils.rejectIfEmptyOrWhitespace (errors,"url","invalid.url" );
        }

        if (target instanceof DocumentResource){
            ValidationUtils.rejectIfEmptyOrWhitespace (errors,"filePath","invalid.filePath");
        }
    }
}
