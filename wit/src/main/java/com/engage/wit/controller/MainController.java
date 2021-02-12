package com.engage.wit.controller;

import com.engage.wit.entity.PairObject;
import com.engage.wit.entity.Post;
import com.engage.wit.entity.Reviews;
import com.engage.wit.entity.User;
import com.engage.wit.exceptions.GenderException;
import com.engage.wit.exceptions.UserNotFound;
import com.engage.wit.services.GetDetails;
import com.engage.wit.services.SaveDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(path="/familyapp")
public class MainController {

    @Autowired
    private GetDetails getDetails;

    @Autowired
    private SaveDetails saveDetails;

    @GetMapping(path="/getallusers")
    public @ResponseBody
    List<User> getAllUserDetails() {
        return getDetails.getAllUsers();
    }

    @GetMapping(path="/getfamily")
    public @ResponseBody
    ArrayList<PairObject> getFamilyDetails(String username, Integer i) throws UserNotFound {
        return getDetails.getFamilyTree(username,i);
    }

    @GetMapping(path="/getAllfamily")
    public @ResponseBody
    ArrayList<User> getAllFamilyDetails(String username) throws UserNotFound {
        return getDetails.getFamily(username);
    }

    @GetMapping(path = "/matchingbloodgroup")
    public @ResponseBody
    List<User> getBloodgroupDetails(String username) throws UserNotFound {
        return getDetails.getBloodGroup(username);
    }

    @GetMapping(path = "/diseases")
    public @ResponseBody
    Map<String, Integer> getDiseases(String username) throws UserNotFound {
        return getDetails.getDiseases(username);
    }

    @GetMapping(path = "/socialindex")
    public @ResponseBody
    Float getSocialIndex(String username) throws UserNotFound {
        return getDetails.getSocialIndex(username);
    }

    @GetMapping(path="/getreviews")
    public @ResponseBody
    ArrayList<Reviews> getReviews(String username) throws UserNotFound {
        return getDetails.getSuggestion(username);
    }

    @GetMapping(path="/getcareeradvise")
    public @ResponseBody
    ArrayList<User> getCareerAdvise(String username, String i) throws UserNotFound {
        return getDetails.getCareerAdvise(username,i);
    }

    @GetMapping(path="/getposts")
    public @ResponseBody
    ArrayList<Post> getPosts(String username) throws UserNotFound {
        return getDetails.getPosts(username);
    }

    @GetMapping(path="/login")
    public @ResponseBody
    User getlogin(String username, String password) throws UserNotFound {
        return getDetails.getLogin(username, password);
    }



    //------------------------------------

    @PostMapping("/adduser")
    public @ResponseBody
    ResponseEntity<?> addUser(@RequestBody User request) throws GenderException {
        saveDetails.addUser(request);
        return new ResponseEntity("Entry added successfully", HttpStatus.OK);
    }

    @PostMapping("/addAlluser")
    public @ResponseBody
    ResponseEntity<?> addUser() throws IOException {
        saveDetails.addAllUser();
        return new ResponseEntity("Entry added successfully", HttpStatus.OK);
    }

    @PostMapping("/addPost")
    public @ResponseBody
    ResponseEntity<?> addPost(@RequestBody Post post) throws IOException, UserNotFound {
        saveDetails.addPosts(post);
        return new ResponseEntity("Post added successfully", HttpStatus.OK);
    }

    @PostMapping("/addReview")
    public @ResponseBody
    ResponseEntity<?> addReview(@RequestBody Reviews reviews) throws IOException, UserNotFound {
        saveDetails.addReview(reviews);
        return new ResponseEntity("Review added successfully", HttpStatus.OK);
    }

    //---------------------------------------

    //@Scheduled(cron = "0 15 10 15 * ?")
    @GetMapping("/getEvents")
    public @ResponseBody
    ArrayList<ArrayList<User>> checkEvent(String username) throws UserNotFound {
        return getDetails.getEvents(username);
    }

    //@Scheduled(cron = "0 15 10 15 * ?")
    @GetMapping("/getHobbies")
    public @ResponseBody
    Map<String,ArrayList<User>> checkHobbies(String username) throws UserNotFound {
        return getDetails.getHobbies(username);
    }



}
// https://stackoverflow.com/questions/50510595/how-to-retrieve-geographical-location-from-users-using-angular

