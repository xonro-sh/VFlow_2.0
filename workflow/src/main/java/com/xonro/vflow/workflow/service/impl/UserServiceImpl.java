package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.workflow.bean.UserInfo;
import com.xonro.vflow.workflow.service.UserService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author louie
 * @date created in 2018-3-14 22:01
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IdentityService identityService;

    @Override
    @CachePut(value = "user",key = "#userId",unless = "#result eq null ")
    public User saveUser(String userId, String firstName, String lastName, String email, String password) {

        User user = identityService.createUserQuery().userId(userId).singleResult();
        if (user == null){
            user = identityService.newUser(userId);;
        }
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
    @CachePut(value = "user",key = "'picture_'+#userId",unless = "#result eq null ")
    public Picture setUserPicture(String userId,byte[] pictureData, String pictureName) {
        Picture picture = new Picture(pictureData,pictureName);
        identityService.setUserPicture(userId,picture);
        return picture;
    }

    @Override
    @Cacheable(value = "user",key = "'picture_'+#userId",unless = "#result eq null ")
    public Picture getUserPicture(String userId) {
        return identityService.getUserPicture(userId);
    }

    @Override
    @CachePut(value = "user",key = "'info_'+#userInfo.userId")
    public UserInfo saveUserInfo(UserInfo userInfo) {
        String userId = userInfo.getUserId();
        try {
            Field[] fields = UserInfo.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (field.get(userInfo) != null){
                    identityService.setUserInfo(userId,fieldName,field.get(userInfo).toString());
                }
            }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        }
        return userInfo;
    }

    @Override
    @Cacheable(value = "user",key = "'info_'+#userId")
    public UserInfo getUserInfo(String userId) {
        List<String> infoKeys = identityService.getUserInfoKeys(userId);
        UserInfo userInfo = new UserInfo();;
        try {
            for (String infoKey : infoKeys) {
                String info = identityService.getUserInfo(userId,infoKey);
                Field[] fields = UserInfo.class.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getName().equals(infoKey)){
                        if (field.getGenericType() == boolean.class){
                            field.set(userInfo,Boolean.valueOf(info));
                        }else if (field.getGenericType() == Integer.class){
                            field.set(userInfo,Integer.valueOf(info));
                        }else {
                            field.set(userInfo,info);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        }
        return userInfo;
    }

    @Override
    public List<User> userInGroup(String groupId) {
        return identityService.createUserQuery().memberOfGroup(groupId).list();
    }

    @Override
    public List<User> userInGroupPage(String groupId, Integer firstResult, Integer limit) {
        return identityService.createUserQuery().memberOfGroup(groupId).listPage(firstResult,limit);
    }


}
