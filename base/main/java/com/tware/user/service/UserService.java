package com.tware.user.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tware.common.utils.PasswordHelper;
import com.tware.permission.entity.Permission;
import com.tware.user.entity.QUser;
import com.tware.user.entity.User;
import com.tware.user.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

@Service
public class UserService extends BaseService<UserRepository, User> {
    // 日志对象
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /** 根据用户token查找用户
     * @return
     */
    public User getUserBytoken(String token){
        User user = null;
        QUser qUser = QUser.user;
        user = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qUser.token.eq(token));
                });
        return user;
    }
    
    /** 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    public User getUserByUsernameAndPassword(String username,String password){
        User user = null;
        QUser qUser = QUser.user;
        user = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qUser.username.eq(username)
                    .and(qUser.password.eq(password)));
                });
        return user;
    }
    
    
    

    /** 根据用户名查找用户
     * @param username
     * @return
     */
    public User getUserByUsername(String username){
        User user = null;
        QUser qUser = QUser.user;
        user = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qUser.username.eq(username));
                });
        return user;
    }

    public void updatePassword(String oldPassword,String newPassword){
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipals().getPrimaryPrincipal();

        user = this.getUserByUsername(user.getUsername());

        oldPassword = PasswordHelper.encryptPassword(oldPassword);
        if(!oldPassword.equals(user.getPassword())){
           throw new SwallowException("原密码不正确");
        }
        newPassword = PasswordHelper.encryptPassword(newPassword);
        user.setPassword(newPassword);
        updateItem(user);
    }

    /** 根据用户Id获取用户的所有权限
     * @param userId
     * @return
     */
    public List<Permission> getAllPermissionByUserId(long userId) {
        return getRepsitory().getAllPermissionByUserId(userId);
    }

    @Override
    public PageListData<User> getAllItemPageByQuerybean(BasePageQueryBean queryBean) {
        return super.getAllItemPageByQuerybean(queryBean);
    }

    /** 根据机构查找用户
     * @param orgId
     * @return
     */
    public User getUserByOrgIdAndType(String orgId, int type){
        User user = null;
        QUser qUser = QUser.user;
        user = getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qUser.orgId.eq(orgId).and(qUser.type.eq(type)));
                });
        return user;
    }

    public User insertItem(String orgId, String code, int type){
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        String username =  "lhxq" + code;
        User admin = this.getUserByUsername(username);// 查看账号是否存在
        User isExist = this.getUserByOrgIdAndType(orgId, 2);// 查看是否已经存在唯一的管理员账号
        if (admin != null || isExist != null) {
           // log.warn("管理员账号：" + username + "创建失败！！！");
            throw new SwallowException(code + "编码已存在，请重新修改！");
        }
        User user = new User();
        user.setOrgId(orgId);
        user.setName(username);
        user.setUsername(username);
        user.setPassword(PasswordHelper.encryptPassword("111111"));
        user.setType(type);
        return super.insertItem(user);
    }
}
