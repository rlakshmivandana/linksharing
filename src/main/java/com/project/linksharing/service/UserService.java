package com.project.linksharing.service;

import com.project.linksharing.model.ResponseDTO;
import com.project.linksharing.model.User;
import com.project.linksharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public ResponseDTO save(User user) throws ParseException {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not Saved");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateWithoutTime = dateFormat.parse(dateFormat.format(new Date()));
        user.setCreatedBy ( user.getUsername ());
        user.setLastUpdatedBy ( user.getUsername () );
        user.setDateCreated (dateWithoutTime);
        user.setLastUpdated (dateWithoutTime  );
        User userData = userRepository.save(user);
        if(userData != null) {
            responseDTO.setStatus ( true );
            responseDTO.setMessage ( "Record Saved" );
          }
        return responseDTO;
    }

    public ResponseDTO get(Long id) {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not found");

        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            responseDTO.setStatus(true);
            responseDTO.setMessage("Record Found");
            responseDTO.setData(optional.get());
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO getAll() {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not found");

        List<User> user =  userRepository.findAll();
        if ((user != null) && !user.isEmpty()){
            responseDTO.setStatus(true);
            responseDTO.setMessage("Record Found");
            responseDTO.setData(user);
        }
        return responseDTO;
    }

    public ResponseDTO delete(Long id) {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not found");

        userRepository.deleteById (id);
        responseDTO.setStatus(true);
        responseDTO.setMessage("Record Deleted");

        return responseDTO;

    }



}
