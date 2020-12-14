package com.tware.role.service;

import com.tware.role.entity.QRole;
import com.tware.role.entity.Role;
import com.tware.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class RoleService extends BaseService<RoleRepository, Role> {
    /** 根据角色名获取角色
     * @param name
     * @return
     */
    public Role getRoleByRoleName(String name) {
        QRole qRole = QRole.role;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qRole.name.eq(name));
                });
    }
}
