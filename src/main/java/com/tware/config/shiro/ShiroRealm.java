package com.tware.config.shiro;

import java.util.List;

import com.tware.kindergarten.entity.SysKindergarten;
import com.tware.kindergarten.service.SysKindergartenService;
import com.tware.user.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.tware.common.utils.PasswordHelper;
import com.tware.permission.entity.Permission;
import com.tware.role.entity.Role;
import com.tware.user.service.UserRoleRelationService;
import com.tware.user.service.UserService;


public class ShiroRealm extends AuthorizingRealm {
    @Autowired
   	private UserService userService;
    @Autowired
    private UserRoleRelationService userRoleRelationService;
    @Autowired
    private SysKindergartenService kindergartenService;
    public ShiroRealm() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        this.setCredentialsMatcher(matcher);
    }

    // 授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User)principals.getPrimaryPrincipal();
       // if(user.getType()==1)
        if(1==1)
        {
       
        List<Role> roleList = userRoleRelationService.getAllRoleByUserId(user.getId());
        boolean isSuperAdmin = false;
        if(null==roleList) {
            return null;
        }
        for(Role role:roleList) {
            if(role.getName()!=null && role.getName().toUpperCase().equals("ADMIN")){
                isSuperAdmin = true;
            }
            authorizationInfo.addRole(role.getName());
        }
        List<Permission> permissionList = userService.getAllPermissionByUserId(user.getId());
       
        if(null==permissionList) {
        	// System.out.println("*********permissionList:0");
            return null;
        }
        for(Permission permission:permissionList) {
        	//System.out.println("#############:"+permission.getCode());
            authorizationInfo.addStringPermission(permission.getCode().trim());
        }
        
        if(isSuperAdmin){
            authorizationInfo.addStringPermission("SUPERADMIN");
        }
     	
        }
        return authorizationInfo;
    }

    // 认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    	
    	SimpleAuthenticationInfo    authenticationInfo =null;
    	  String username =(String)token.getPrincipal();
          String password = new String((char[])token.getCredentials());
        User user = userService.getUserByUsername(username);
        System.out.println("wwwww");
        if(null==user) {
            throw new UnknownAccountException("用户不存在");
        }
        if(user.getDisable()!=0) {
             throw new LockedAccountException("用户已经被锁定");
        }
        if (user.getType() ==3) {// 教职工用户
            SysKindergarten kindergarten = kindergartenService.getSysKindergartenByOrgId(user.getOrgId());
            if (kindergarten != null && kindergarten.getStatus() != 0) {// -1删除；0开启；1关闭
                throw new LockedAccountException("用户已经被锁定");
            }
        }
             
        try {

            user.setLoginUserid(user.getId().intValue());

                authenticationInfo = new SimpleAuthenticationInfo(
                 user, // 用户
                 PasswordHelper.encryptPassword(user.getPassword()), //密码
                 ByteSource.Util.bytes("test"),//salt
                 getName()  //realm name
         );

        }catch (Exception e) {
             throw new UnknownAccountException("用户不存在");
        }
       
        return authenticationInfo;
    }
}
