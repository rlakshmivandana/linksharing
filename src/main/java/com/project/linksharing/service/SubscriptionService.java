package com.project.linksharing.service;

import com.project.linksharing.model.ResponseDTO;
import com.project.linksharing.model.Subscription;
import com.project.linksharing.model.Topic;
import com.project.linksharing.model.User;
import com.project.linksharing.repository.SubscriptionRepository;
import com.project.linksharing.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService  {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    HttpSession httpSession;

    public ResponseDTO save(Subscription subscription) {

        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not Saved");
        Optional<Topic> topic=topicRepository.findById ( subscription.getTopic().getId ());

        Topic topicResource = topic.get ();
        subscription.setTopic (topicResource );
        User sessionUser=(User) httpSession.getAttribute ( "user" );
        subscription.setUser ( sessionUser );
        Subscription subscriptionData = subscriptionRepository.save ( subscription );
        if(subscription != null) {
            responseDTO.setStatus ( true );
            responseDTO.setMessage ( "Subscription Record Saved" );
            responseDTO.setData (subscriptionData);
        }

        return responseDTO;

    }

    public ResponseDTO get(Long id) {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not found");
        Optional<Subscription> optional = subscriptionRepository.findById(id);
        if (optional.isPresent()) {
            responseDTO.setStatus(true);
            responseDTO.setMessage("Subscription Record Found");
            responseDTO.setData(optional.get());
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO getAll() {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not found");
        List<Subscription> subscription =  subscriptionRepository.findAll();
        if ((subscription != null) && !subscription.isEmpty()){
            responseDTO.setStatus(true);
            responseDTO.setMessage("Subscription Records Found");
            responseDTO.setData(subscription);
        }
        return responseDTO;

    }

    public ResponseDTO delete(Long id) {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not found");
        subscriptionRepository.deleteById (id);
        responseDTO.setStatus(true);
        responseDTO.setMessage("Subscription Records Deleted");
        return responseDTO;
    }
}
