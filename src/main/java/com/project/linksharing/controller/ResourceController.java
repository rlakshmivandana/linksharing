package com.project.linksharing.controller;

import com.project.linksharing.model.DocumentResource;
import com.project.linksharing.model.LinkResource;
import com.project.linksharing.model.ResponseDTO;
import com.project.linksharing.service.ResourceService;
import com.project.linksharing.util.ResourceDTO;
import com.project.linksharing.util.ResourceValidator;
import com.project.linksharing.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @Autowired
    MessageSource messageSource;


    @PostMapping(value="/save", produces = "application/json")
    public ResponseDTO save(ResourceDTO resource, BindingResult bindingResult){
        new ResourceValidator ().validate ( resource,bindingResult );
        if(bindingResult.hasErrors ()){
            return UtilValidator.getErrors ( bindingResult, messageSource );
        }
        return resourceService.save(resource);
    }

    @GetMapping(value="get/{id}", produces="application/json")
    public ResponseDTO get(@PathVariable Long id){
        return resourceService.get(id);
    }

    @GetMapping(value="get", produces="application/json")
    public ResponseDTO getAll(){
        return resourceService.getAll();
    }

    @GetMapping(value="delete/{id}", produces="application/json")
    public ResponseDTO delete(@PathVariable Long id){
        return resourceService.delete(id);
    }

}
