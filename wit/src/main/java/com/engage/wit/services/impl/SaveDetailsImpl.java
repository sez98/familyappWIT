package com.engage.wit.services.impl;

import com.engage.wit.entity.Post;
import com.engage.wit.entity.Reviews;
import com.engage.wit.entity.User;
import com.engage.wit.exceptions.GenderException;
import com.engage.wit.exceptions.UserNotFound;
import com.engage.wit.repository.UserRecordRepository;
import com.engage.wit.services.GetDetails;
import com.engage.wit.services.SaveDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class SaveDetailsImpl implements SaveDetails {
    @Autowired
    private UserRecordRepository userRecordRepository;

    @Autowired
    private GetDetails getDetails;

    @Override
    public void addUser(User object) throws GenderException {

        String mom_username= object.getMom_username();
        String dad_username= object.getDad_username();
        String spouse_username= object.getSpouse_username();
        ArrayList<String> children_username= object.getChildren();

        if(mom_username!=null) {
            Optional<User> Mom = userRecordRepository.findById(mom_username);
            if (Mom.isPresent()) {
                Mom.get().getChildren().add(object.getUsername());
            }
        }

        if(dad_username!=null) {
            Optional<User> Dad = userRecordRepository.findById(dad_username);
            if (Dad.isPresent()) {
                Dad.get().getChildren().add(object.getUsername());
            }
        }

        if(spouse_username!=null) {
            Optional<User> Spouse = userRecordRepository.findById(spouse_username);
            if (Spouse.isPresent()) {
                Spouse.get().setSpouse_username(object.getUsername());
            }
        }

        if(children_username!=null && children_username.size()>0){
            for(int i=0;i<children_username.size();i++){
                Optional<User> child = userRecordRepository.findById(children_username.get(i));
                if(child.isPresent()){
                    if(object.getGender().compareTo("Female")==0)
                        child.get().setMom_username(object.getUsername());
                    else if(object.getGender().compareTo("Male")==0)
                        child.get().setDad_username(object.getUsername());
                    else
                        throw new GenderException("Wrong Gender Entered");
                }
            }
        }
        userRecordRepository.save(object);

    }

    @Override
    public void addAllUser() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        List<User> langList = objectMapper.readValue(
                new File("E:\\Engage WiT Hackathon\\wit\\populateTable.json"),
                new TypeReference<List<User>>(){});

        langList.forEach(x -> userRecordRepository.save(x));
    }

    @Override
    public void addPosts(Post post) throws UserNotFound {
        System.out.println(post.getUsername());
        System.out.println(post.getTopic());
        Optional<User> u=userRecordRepository.findById(post.getUsername());
        if(u.isPresent()){
            ArrayList<User> family = getDetails.getFamily(post.getUsername());
            for(int i=0;i<family.size();i++) {
                System.out.println("family member- " +family.get(i).getUsername());
                if(family.get(i).getPosts()==null){
                    family.get(i).setPosts(new ArrayList<>());
                }
                family.get(i).getPosts().add(post);
                userRecordRepository.save(family.get(i));
            }
        }
    }

    @Override
    public void addReview(Reviews reviews) throws UserNotFound {
        System.out.println(reviews.getUsername());
        System.out.println(reviews.getTopic());
        Optional<User> u=userRecordRepository.findById(reviews.getUsername());
        if(u.isPresent()){
            ArrayList<User> family = getDetails.getFamily(reviews.getUsername());
            for(int i=0;i<family.size();i++) {
                System.out.println("family member- " +family.get(i).getUsername());
                if(family.get(i).getReviews()==null){
                    family.get(i).setReviews(new ArrayList<>());
                }
                family.get(i).getReviews().add(reviews);
                userRecordRepository.save(family.get(i));
            }
        }
    }
}
