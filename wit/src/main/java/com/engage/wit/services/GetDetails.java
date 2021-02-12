package com.engage.wit.services;

import com.engage.wit.entity.*;
import com.engage.wit.exceptions.UserNotFound;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GetDetails {
    List<User> getAllUsers();
    ArrayList<PairObject> getFamilyTree(String username, Integer i) throws UserNotFound;
    List<User> getBloodGroup(String username) throws UserNotFound;
    ArrayList<User> getFamily(String username) throws UserNotFound;
    ArrayList<ArrayList<User>> getEvents(String username) throws UserNotFound;
    Map<String,ArrayList<User>> getHobbies(String username) throws UserNotFound;
    ArrayList<Reviews> getSuggestion(String username) throws UserNotFound;
    ArrayList<User> getCareerAdvise(String username, String topic) throws UserNotFound;
    ArrayList<Post> getPosts(String username);
    Map<String, Integer> getDiseases(String username) throws UserNotFound;
    User getLogin(String username, String password);
    Float getSocialIndex(String username);
}
