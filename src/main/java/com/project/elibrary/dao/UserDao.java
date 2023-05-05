package com.project.elibrary.dao;

import java.util.List;
import com.project.elibrary.models.User;

public interface UserDao {
    public List<User> getAllUsers();

    public User getUserByName(String name);
    public User getUserById(Long id);
    
    public boolean updateUsername(String oldUsername, String newUsername);
    public boolean updatePassword(String username, String newPassword);
    public boolean updateProfilePic(Long userID, String profilePic);
}
