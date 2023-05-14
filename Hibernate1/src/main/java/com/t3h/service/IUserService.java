package com.t3h.service;

import com.t3h.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUser();
    void saveOrUpdate(User user);

    User findUserByID(int id);
    void delete(int id);
}
