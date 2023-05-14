package com.project.elibrary.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.project.elibrary.models.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setProfilePic(rs.getString("profile_pic"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
        });
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, id);
            }
        }, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setProfilePic(rs.getString("profile_pic"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User getUserByName(String name) {
        String sql = "SELECT * FROM user WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, name);
            }
        }, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setProfilePic(rs.getString("profile_pic"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        return users.isEmpty() ? null : users.get(0);
    }

    //edit profile methods
    @Override
    public boolean updateProfilePic(Long userID, String profilePic) {
        String sql = "UPDATE user SET profile_pic = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, profilePic, userID);
        return rowsAffected == 1;
    }

    @Override
    public boolean updateUsername(String oldUsername, String newUsername) {
        String sql = "UPDATE user SET username = ? WHERE username = ?";
        int rowsAffected = jdbcTemplate.update(sql, newUsername, oldUsername);
        return rowsAffected == 1; 
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public boolean updatePassword(Long userID, String newPassword) {
        String sql = "UPDATE user SET password = ? WHERE id = ?";
        String protectedNewPassword = bCryptPasswordEncoder.encode(newPassword);
        int rowsAffected = jdbcTemplate.update(sql, protectedNewPassword, userID);
        return rowsAffected == 1; 
    }

}
