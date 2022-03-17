package com.meng.service;

import com.meng.pojo.User;

public interface UserService {
    /**
     * login
     *
     * @param username
     * @param password
     * @return
     */
    User select(String username, String password);
}
