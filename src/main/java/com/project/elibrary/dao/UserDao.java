package com.project.elibrary.dao;

import java.util.List;
import com.project.elibrary.models.User;

public interface UserDao {
    public List<User> getAllUsers();

    public User getUserByName(String name);
}
