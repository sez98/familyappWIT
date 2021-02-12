package com.engage.wit.services;

import com.engage.wit.entity.Post;
import com.engage.wit.entity.Reviews;
import com.engage.wit.entity.User;
import com.engage.wit.exceptions.GenderException;
import com.engage.wit.exceptions.UserNotFound;

import java.io.IOException;

public interface SaveDetails {
    void addUser(User object) throws GenderException;
    void addAllUser() throws IOException;
    void addPosts(Post post) throws UserNotFound;
    void addReview(Reviews reviews) throws UserNotFound;
}
