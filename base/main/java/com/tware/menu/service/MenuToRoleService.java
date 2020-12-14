package com.tware.menu.service;

import com.tware.menu.entity.MenuToRole;
import com.tware.menu.entity.QMenuToRole;
import com.tware.menu.repository.MenuToRoleRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class MenuToRoleService extends BaseService<MenuToRoleRepository, MenuToRole> {

    public void deleteItemByMenuId(Long menuId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QMenuToRole.menuToRole.menuId.eq(menuId)));
    }
}
