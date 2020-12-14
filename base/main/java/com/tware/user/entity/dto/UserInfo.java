package com.tware.user.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.tware.user.entity.User;

public class UserInfo extends User {
    private List<String> roleList = new ArrayList<>();
    private List<String> permissionList = new ArrayList<>();

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }
}
