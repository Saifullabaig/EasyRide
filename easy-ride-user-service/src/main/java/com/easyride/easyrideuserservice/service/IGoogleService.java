package com.easyride.easyrideuserservice.service;


import com.easyride.easyrideuserservice.exception.UserNotFoundException;
import com.easyride.easyrideuserservice.model.User;
import com.mongodb.DuplicateKeyException;

public interface IGoogleService {

    String getAccessToken(String code) throws UserNotFoundException;

    User getUserProfile(String accessToken) throws UserNotFoundException;

    void addUserData(User user) throws DuplicateKeyException;

}
