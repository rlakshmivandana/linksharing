package com.project.linksharing.controller;

import com.project.linksharing.model.ResponseDTO;
import com.project.linksharing.model.Subscription;
import com.project.linksharing.service.SubscriptionService;
import com.project.linksharing.util.ResourceDTO;
import com.project.linksharing.util.ResourceValidator;
import com.project.linksharing.util.SubscriptionValidator;
import com.project.linksharing.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    MessageSource messageSource;


    @PostMapping(value="/save", produces = "application/json",consumes = "application/json")
    public ResponseDTO save(@ModelAttribute Subscription subscription, BindingResult bindingResult){
        new SubscriptionValidator ().validate ( subscription,bindingResult );
        if(bindingResult.hasErrors ()){
            return UtilValidator.getErrors ( bindingResult, messageSource );
        }
        return subscriptionService.save(subscription);
    }

    @GetMapping(value="get/{id}", produces="application/json")
    public ResponseDTO get(@PathVariable Long id){
        return subscriptionService.get(id);
    }

    @GetMapping(value="get", produces="application/json")
    public ResponseDTO getAll(){
        return subscriptionService.getAll();
    }

    @GetMapping(value="delete/{id}", produces="application/json")
    public ResponseDTO delete(@PathVariable Long id){
        return subscriptionService.delete(id);
    }

}
