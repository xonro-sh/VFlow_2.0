package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.CreateUser;
import com.xonro.vflow.workflow.bean.Department;
import com.xonro.vflow.workflow.bean.Role;
import com.xonro.vflow.workflow.bean.UserInfo;
import com.xonro.vflow.workflow.dao.DepartmentRepository;
import com.xonro.vflow.workflow.enums.OrgEnum;
import com.xonro.vflow.workflow.service.RoleService;
import com.xonro.vflow.workflow.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author louie
 * @date created in 2018-3-14 22:01
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private IdentityService identityService;
    @Resource
    private DepartmentRepository departmentRepository;
    @Resource
    private RoleService roleService;

    @Override
    @CachePut(value = "user",key = "#createUser.userId",unless = "#result eq null ")
    public User createUser(CreateUser createUser) {
        String userId = createUser.getUserId();
        User user = identityService.newUser(userId);

        user.setPassword(DigestUtils.md5Hex(createUser.getPassword()));
        user.setFirstName(createUser.getFirstName());
        user.setLastName(createUser.getLastName());
        String email = createUser.getEmail();
        if (StringUtils.isNotEmpty(email)){
            user.setEmail(email);
        }
        identityService.saveUser(user);
        identityService.setUserInfo(userId,"tenantId",createUser.getTenantId());
        return user;
    }

    @Override
    public BaseResponse setUserActive(String userId, boolean active) throws VFlowException {
        User user = identityService.createUserQuery().userId(userId).singleResult();
        try {
            if (user != null){
                identityService.setUserInfo(userId,"active",active+"");
                return new BaseResponse(true,"SUCCESS","SUCCESS");
            }
            throw new VFlowException("fail","user not exist,userId:"+userId);
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public BaseResponse changeUserPassword(String userId, String oldPassword, String newPassword) {
        if (identityService.checkPassword(userId,DigestUtils.md5Hex(oldPassword))){
            User user = identityService.createUserQuery().userId(userId).singleResult();
            user.setPassword(DigestUtils.md5Hex(newPassword));
            identityService.saveUser(user);
            return new BaseResponse(true,user);
        }
        return new BaseResponse(false,"fail","old password is wrong");
    }

    @Override
    @CachePut(value = "user",key = "#userId",unless = "#result eq null ")
    public User updateUser(String userId, String firstName, String lastName, String email) throws VFlowException {
        User user = identityService.createUserQuery().userId(userId).singleResult();
        try {
            if (user == null){
                throw new VFlowException("fail","user not exist,userId:"+userId);
            }

            if (StringUtils.isNotEmpty(firstName)){
                user.setFirstName(firstName);
            }
            if (StringUtils.isNotEmpty(lastName)){
                user.setLastName(lastName);
            }
            if (StringUtils.isNotEmpty(email)){
                user.setEmail(email);
            }
            identityService.saveUser(user);
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
        return user;
    }

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
        user.setPassword(DigestUtils.md5Hex(password));

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
    public BaseResponse deleteUserById(String userId) {
        identityService.deleteUser(userId);
        return new BaseResponse(true,"success","");
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
    public UserInfo saveUserInfo(UserInfo userInfo) throws VFlowException, IllegalAccessException {
        String userId = userInfo.getUserId();
        User user = identityService.createUserQuery().userId(userId).singleResult();
        try {
            if (user == null){
                throw new VFlowException("fail","user not exist,userId:"+userId);
            }

            Field[] fields = UserInfo.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (field.get(userInfo) != null){
                    identityService.setUserInfo(userId,fieldName,field.get(userInfo).toString());
                }
            }
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(),e);
            throw e;
        }catch (VFlowException e){
            log.error(e.getMessage(),e);
            throw e;
        }
        return userInfo;
    }

    @Override
    public boolean checkUserPassword(String userId, String password) {
        return identityService.checkPassword(userId,DigestUtils.md5Hex(password));
    }

    @Override
    @Cacheable(value = "user",key = "'info_'+#userId")
    public UserInfo getUserInfo(String userId) throws VFlowException, IllegalAccessException {
        User user = identityService.createUserQuery().userId(userId).singleResult();
        try {
            if (user == null){
                throw new VFlowException("fail","user not exist,userId:"+userId);
            }
            List<String> infoKeys = identityService.getUserInfoKeys(userId);
            UserInfo userInfo = new UserInfo();

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
            return userInfo;
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(),e);
            throw e;
        }catch (VFlowException e){
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public BaseResponse setUserDepartment(String userId, String departmentId) throws VFlowException {
        Department department = departmentRepository.findById(departmentId);
        try {
            if (department == null){
                throw new VFlowException("error","department not exist,departmentId:"+departmentId);
            }
            //删除原有用户-部门关系
            Group group = identityService.createGroupQuery().groupMember(userId).groupType(OrgEnum.GROUP_TYPE_DEPARTMENT.getValue()).singleResult();
            if (group != null){
                identityService.deleteMembership(userId,group.getId());
            }
            //创建新的部门-用户关系
            identityService.createMembership(userId,department.getGroupId());
            return new BaseResponse(true,"success","");
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public Department userDepartment(String userId) {
        Group group = identityService.createGroupQuery().groupType(OrgEnum.GROUP_TYPE_DEPARTMENT.getValue()).groupMember(userId).singleResult();
        if (group == null){
            return null;
        }
        return departmentRepository.findByGroupId(group.getId());
    }

    @Override
    public BaseResponse setUserRole(String userId, String roleId) throws VFlowException {
        Role role = roleService.getRoleById(roleId);
        try {
            if (role == null){
                throw new VFlowException("fail","role not exist,roleId:"+roleId);
            }
            //删除旧角色
            Group group = identityService.createGroupQuery().groupType(OrgEnum.GROUP_TYPE_ROLE.getValue()).groupMember(userId).singleResult();
            identityService.deleteMembership(userId,group.getId());

            //创建新角色
            identityService.createMembership(userId,role.getGroupId());
            return new BaseResponse(true,"success","");
        } catch (VFlowException e) {
            log.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public Role getUserRole(String userId) {
        Group group = identityService.createGroupQuery().groupType(OrgEnum.GROUP_TYPE_ROLE.getValue()).groupMember(userId).singleResult();
        if (group == null){
            return null;
        }
        return roleService.getRoleByGroupId(group.getId());
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
