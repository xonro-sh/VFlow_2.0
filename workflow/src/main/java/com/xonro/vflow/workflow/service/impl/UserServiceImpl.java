package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.workflow.service.UserService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author louie
 * @date created in 2018-3-14 22:01
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IdentityService identityService;

    @Override
    @CachePut(value = "user",key = "#userId",unless = "#result eq null ")
    public User saveUser(String userId, String firstName, String lastName, String email, String password) {
        User user = identityService.newUser(userId);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);

        identityService.saveUser(user);
        return user;
    }

    @Override
    @Cacheable(value = "user",key = "#userId",unless = "#result eq null ")
    public User findUserById(String userId) {
        return identityService.createUserQuery().userId(userId).singleResult();
    }

    @Override
    @CacheEvict(value = "user",key = "#userId")
    public void deleteUserById(String userId) {
        identityService.deleteUser(userId);
    }

    @Override
    public List<User> findAll() {
        return identityService.createUserQuery().list();
    }

    @Override
    public List<User> listPage(Integer offset, Integer limit) {
        return identityService.createUserQuery().listPage(offset,limit);
    }

    @Override
    public Picture setUserPicture(String userId,byte[] pictureData, String pictureName) {
        Picture picture = new Picture(pictureData,pictureName);
        identityService.setUserPicture(userId,picture);
        return picture;
    }
}
