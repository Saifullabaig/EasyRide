package com.easyride.easyrideuserservice.service;


import javax.servlet.http.HttpServletRequest;

import com.easyride.easyrideuserservice.model.User;


public interface IUserService {

    User loadByUsername(HttpServletRequest request);

}
