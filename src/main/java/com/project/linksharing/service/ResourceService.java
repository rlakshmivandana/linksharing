package com.project.linksharing.service;

import com.project.linksharing.model.*;
import com.project.linksharing.repository.ResourceRepository;
import com.project.linksharing.repository.TopicRepository;
import com.project.linksharing.util.ResourceDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ResourceRepository resourceRepository;

    public ResponseDTO save(ResourceDTO resourceDTO) {
        ResponseDTO responseDTO = new ResponseDTO (false, " Link Record Not Saved");
        Resource resource=null;
        if(StringUtils.isEmpty (resourceDTO.getUrl ())){
            resource=new DocumentResource ();

        }else {
            resource=new LinkResource ();
        }

        // BeanUtils.copyProperties ( resource,resourceDTO );
        BeanUtils.copyProperties ( resourceDTO, resource);

        Optional<Topic> topic=topicRepository.findById ( resourceDTO.getTopic ().getId ());
        Topic topicResource = topic.get ();
        topicResource. getResources ( ).add ( resource );

        Topic topic1 = topicRepository.save(topicResource);
        if(topic1!=null){
            responseDTO.setStatus ( true );
            responseDTO.setMessage ( " Link Record Saved" );
            responseDTO.setData (topic1);
        }

        return responseDTO;
    }

    public ResponseDTO get(Long id) {
        ResponseDTO responseDTO = new ResponseDTO (false, "Resource Record Not found");
        Optional<Resource> optional = resourceRepository.findById(id);
        if (optional.isPresent()) {
            responseDTO.setStatus(true);
            responseDTO.setMessage("Resource Record Found");
            responseDTO.setData(optional.get());
            return responseDTO;
        }
        return responseDTO;
    }

    public ResponseDTO getAll() {
        ResponseDTO responseDTO = new ResponseDTO (false, "Resource Record Not found");
        List<Resource> resource =  resourceRepository.findAll();
        if ((resource != null) && !resource.isEmpty()){
            responseDTO.setStatus(true);
            responseDTO.setMessage("Resource Records Found");
            responseDTO.setData(resource);
        }
        return responseDTO;
    }

    public ResponseDTO delete(Long id) {
        ResponseDTO responseDTO = new ResponseDTO (false, "Resource Record Not found");

        resourceRepository.deleteById(id);

        responseDTO.setStatus(true);
        responseDTO.setMessage("Resource Records Deleted");
        return responseDTO;

    }
}
