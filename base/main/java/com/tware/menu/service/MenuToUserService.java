package com.tware.menu.service;


import com.tware.menu.entity.MenuToUser;
import com.tware.menu.entity.QMenuToUser;
import com.tware.menu.repository.MenuToUserRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class MenuToUserService extends BaseService<MenuToUserRepository, MenuToUser> {

    public void deleteItemByMenuId(Long menuId){
        getRepsitory().deleteEntityByColumns(
                query -> query.where(QMenuToUser.menuToUser.menuId.eq(menuId)));
    }
}
