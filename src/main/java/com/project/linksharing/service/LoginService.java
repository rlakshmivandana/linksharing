package com.project.linksharing.service;

import com.project.linksharing.model.ResponseDTO;
import com.project.linksharing.model.User;
import com.project.linksharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession httpSession;

    public ResponseDTO authenticateUser(User user) {
        ResponseDTO responseDTO = new ResponseDTO (false, "Login Failed");
System.out.println("authenticate user.............................");
        Optional<User> userDetails = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
       // System.out.println("userDetails"+userDetails.get ());

        if (userDetails.isPresent()) {
            httpSession.setAttribute("user" , userDetails.get () );
            responseDTO.setStatus(true);
            responseDTO.setMessage("Login Successful");
            responseDTO.setData(userDetails.get());
            return responseDTO;
        }
        System.out.println("----------------------------------------------responseDTO"+responseDTO.getData ());

        return responseDTO;
    }
}
