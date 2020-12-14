package com.tware.user.entity.vo;

import com.tware.permission.entity.Permission;

public class PermissionOfRoleVo extends Permission {
    private long rolePermissionRelationId;

    public long getRolePermissionRelationId() {
        return rolePermissionRelationId;
    }

    public void setRolePermissionRelationId(long rolePermissionRelationId) {
        this.rolePermissionRelationId = rolePermissionRelationId;
    }
}
