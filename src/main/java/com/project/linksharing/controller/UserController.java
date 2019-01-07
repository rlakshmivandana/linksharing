package com.project.linksharing.controller;
import com.project.linksharing.model.ResponseDTO;
import com.project.linksharing.model.User;
import com.project.linksharing.service.UserService;
import com.project.linksharing.util.LoginValidator;
import com.project.linksharing.util.UserValidator;
import com.project.linksharing.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    @GetMapping(value="/register")
    public ModelAndView getRegisterPage() {

        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject ( "user",new User () );
        return modelAndView;
    }

    @PostMapping(value="/save")
    public ModelAndView saveUserDetails(@Valid @ModelAttribute User user, BindingResult bindingResult)throws ParseException  {
        ResponseDTO responseDTO;
        ModelAndView modelAndView;
       new UserValidator ().validate(user,bindingResult);
        if(bindingResult.hasErrors ()){
            responseDTO =  UtilValidator.getErrors(bindingResult, messageSource);
            modelAndView = new ModelAndView ( "register" );
            modelAndView.addObject ( "user",user);
            return modelAndView;

        }
        responseDTO = userService.save(user);
        modelAndView = new ModelAndView ( "login" );
        return modelAndView;
    }

/*    @PostMapping(value="/save", produces = "application/json")
    public ResponseDTO save(@ModelAttribute User user, BindingResult bindingResult) throws ParseException {
        new UserValidator ().validate(user,bindingResult);
        if(bindingResult.hasErrors ()){
            return UtilValidator.getErrors(bindingResult, messageSource);

        }
        return  userService.save(user);
    }*/

    @GetMapping(value="/get/{id}")
    public ResponseDTO get(@PathVariable Long id){
        return userService.get(id);
    }

    @GetMapping("/get")
    public ResponseDTO getAll() {
        return userService.getAll();
    }

    @PostMapping(value = "/update", produces = "application/json")
    public ResponseDTO update(@ModelAttribute User user) throws ParseException {
        return  userService.save(user);
    }

    @DeleteMapping(value = "/delete", produces = "application/json")
    public ResponseDTO delete(@PathVariable Long id) throws ParseException {
        return  userService.delete(id);
    }
}
