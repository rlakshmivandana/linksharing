package com.project.linksharing.service;

import com.project.linksharing.model.ResponseDTO;
import com.project.linksharing.model.Topic;
import com.project.linksharing.model.User;
import com.project.linksharing.repository.SubscriptionRepository;
import com.project.linksharing.repository.TopicRepository;
import com.project.linksharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    HttpSession httpSession;
@Autowired
    SubscriptionRepository subscriptionRepository;

@Autowired
    UserRepository userRepository;
    public ResponseDTO save(Topic topic) throws ParseException {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not Saved");

        DateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
        Date dateWithoutTime = dateFormat.parse(dateFormat.format(new Date()));
        topic.setDateCreated (dateWithoutTime);
        topic.setLastUpdated (dateWithoutTime  );


        User sessionUser=(User) httpSession.getAttribute ( "user" );
        User user=userRepository.findById ( sessionUser.getId () ).get ();
        topic.setCreatedBy ( user.getUsername ());
        topic.setLastUpdatedBy ( user.getUsername () );
        List<Topic>topics=user.getTopics ();
        topics.add ( topic );
        User topicUser = userRepository.save ( user );
        if (topicUser != null) {
            responseDTO.setStatus(true);
            responseDTO.setMessage("Topic Data Saved");
            responseDTO.setData(topicUser);
        }
        return responseDTO;
    }

    public ResponseDTO get(Long id) {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not found");
        Optional<Topic> optional = topicRepository.findById(id);
        if (optional.isPresent()) {
            responseDTO.setStatus(true);
            responseDTO.setMessage("Topic Record Found");
            responseDTO.setData(optional.get());
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO getAll() {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not found");
        List<Topic> topic =  topicRepository.findAll();
        if ((topic != null) && !topic.isEmpty()){
            responseDTO.setStatus(true);
            responseDTO.setMessage("Topic Records Found");
            responseDTO.setData(topic);
        }
        return responseDTO;
    }

    public ResponseDTO delete(Long id) {
        ResponseDTO responseDTO = new ResponseDTO (false, "Record Not found");
       topicRepository.deleteById (id);
      /*  User SESSIONuser=new User ();
     User user=userRepository.findById ( SESSIONuser.getId () ).get ();
     Topic topic=topicRepository.findById ( id ).get ();

     subscriptionRepository.findByUserAndTopic (user,topic );
     subscriptionRepository.flush ();
      List<Topic>topics=user.getTopics ();
      topics.removeIf ( it->it.getId ().equals ( id ) );
      user.setTopics ( topics );
      userRepository.save ( user );
        responseDTO.setStatus(true);
        responseDTO.setMessage("Topic Record Found");*/
        return responseDTO;

    }
}
