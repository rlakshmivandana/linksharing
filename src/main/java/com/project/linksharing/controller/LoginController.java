package com.project.linksharing.controller;

import com.project.linksharing.model.ResponseDTO;
import com.project.linksharing.model.User;
import com.project.linksharing.service.LoginService;
import com.project.linksharing.service.UserService;
import com.project.linksharing.util.LoginValidator;
import com.project.linksharing.util.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;

//@Controller
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserService userService;

    @GetMapping(value="/")
    public ModelAndView getLoginPage(@Valid @ModelAttribute User user, BindingResult bindingResult)throws ParseException {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject ( "user",new User () );
        return modelAndView;
    }

    @PostMapping(value="/authenticate")
    public ModelAndView authenticateUser(@Valid @ModelAttribute User user, BindingResult bindingResult)throws ParseException {
        ModelAndView modelAndView;
        ResponseDTO responseDTO;

        new LoginValidator().validate(user,bindingResult);
        if(bindingResult.hasErrors ()){
            responseDTO = UtilValidator.getErrors(bindingResult, messageSource);
            modelAndView = new ModelAndView ( "login" );
            modelAndView.addObject ( "user",user);
            return modelAndView;

        }
         responseDTO =loginService.authenticateUser(user);
         user = (User) responseDTO.getData ();
        if(responseDTO.getStatus ().equals ( false )){
            modelAndView = new ModelAndView ( "login" );
        }else {
            modelAndView = new ModelAndView ( "index" );
            modelAndView.addObject ( "user", user );

        }

        return modelAndView;
    }

    @PostMapping(value="/user")
    public ResponseDTO login(@ModelAttribute User user, BindingResult bindingResult){
        new LoginValidator().validate(user,bindingResult);
        if(bindingResult.hasErrors ()){
            return UtilValidator.getErrors(bindingResult, messageSource);

        }
        return loginService.authenticateUser(user);
    }

}
