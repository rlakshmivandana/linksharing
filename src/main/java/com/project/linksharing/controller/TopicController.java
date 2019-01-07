package com.project.linksharing.controller;

import com.project.linksharing.model.ResponseDTO;
import com.project.linksharing.model.Topic;

import com.project.linksharing.service.TopicService;
import com.project.linksharing.util.TopicValidator;
import com.project.linksharing.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    MessageSource messageSource;

    @PostMapping(value="/save", produces = "application/json")
    public ResponseDTO save(@ModelAttribute Topic topic, BindingResult bindingResult) throws ParseException {

        new TopicValidator ().validate ( topic, bindingResult );
        if(bindingResult.hasErrors ()){
            return  UtilValidator.getErrors ( bindingResult, messageSource );
        }
        return  topicService.save(topic);
    }

    @GetMapping(value="/get/{id}")
    public ResponseDTO get(@PathVariable Long id){
        return topicService.get(id);
    }

    @GetMapping("/get")
    public ResponseDTO getAll() {
        return topicService.getAll();
    }

    @PostMapping(value = "/update", produces = "application/json")
    public ResponseDTO update(@ModelAttribute Topic topic) throws ParseException {
        return  topicService.save(topic);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseDTO delete(@PathVariable Long id) {
        return  topicService.delete(id);
    }

}
