package com.quiz.service;

import com.quiz.dao.UserDao;
import com.quiz.entities.NotificationStatus;
import com.quiz.entities.User;
import com.quiz.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        User userdb = userDao.findByEmail(email);
        if (userdb == null) {
            throw new NotFoundException("user", "email", email);
        }
        return userdb;
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public User findProfileInfoByUserId(int id) {
        return userDao.findProfileInfoByUserId(id);
    }

    public List<User> findFriendByUserId(int id) {
        return userDao.findFriendByUserId(id);
    }


    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    public boolean updatePasswordById(int id, String newPassword) {
        return userDao.updatePasswordById(id, passwordEncoder.encode(newPassword));
    }

    public int getUserIdByEmail(String email){
        return userDao.getUserIdByEmail(email);
    }

    public boolean updateProfileImage(MultipartFile image, int userId) {
        return userDao.updateProfileImage(image, userId);
    }

    public byte[] getImageByUserId(int userId) {
        return userDao.getUserImageByUserId(userId);
    }

    public boolean changeNotificationStatus(String status, int userId) {
        return userDao.updateNotificationStatus(status, userId);
    }

    public NotificationStatus getNotificationStatus(int userId) {
        return userDao.getUserNotification(userId);
    }
}
