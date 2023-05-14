package com.t3h.service.impl;

import com.t3h.dao.IUserDao;
import com.t3h.dao.impl.UserDaoImpl;
import com.t3h.entity.User;
import com.t3h.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private IUserDao iUserDao;

    public UserServiceImpl(IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }


    @Override
    public List<User> getAllUser() {
        return iUserDao.getAllUsers();
    }

    @Override
    public void saveOrUpdate(User user) {
        iUserDao.saveOrUpdate(user);
    }

    @Override
    public User findUserByID(int id) {
        return iUserDao.findUserById(id);
    }

    @Override
    public void delete(int id) {
        iUserDao.delete(id);
    }
}
