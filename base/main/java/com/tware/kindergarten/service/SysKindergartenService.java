package com.tware.kindergarten.service;

import com.tware.common.service.JDBCDaoImp;
import com.tware.kindergarten.entity.QSysKindergarten;
import com.tware.kindergarten.entity.SysKindergarten;
import com.tware.kindergarten.repository.SysKindergartenRepository;
import com.tware.org.service.SysOrgService;
import com.tware.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SysKindergartenService extends BaseService<SysKindergartenRepository, SysKindergarten> {

    @Autowired
    private UserService userService;

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private JDBCDaoImp jdbcDaoImp;

    public SysKindergarten insertItem(SysKindergarten item){
        String orgId = UUID.randomUUID().toString();
        item.setOrgId(orgId);
        userService.insertItem(orgId, item.getCode(), 2);// 新增管理员账号
        sysOrgService.insertItem(item, 2);// 新增机构信息
        return super.insertItem(item);
    }

    /**
     * 更改幼儿园状态
     * @param ids
     * @param status
     * @return
     */
    public boolean updateStatus(Long[] ids, int status){
        if (ids.length <= 0) {
            return true;
        }
        Map<String, Object> map = new HashMap<>();
        String sql = "update sys_kindergarten k left join user u on u.org_id = k.org_id  " +
                " set k.status = :status,u.disable=:disable where k.id in (:ids) and u.type = 2";
        map.put("status", status);
        map.put("disable", status);
        map.put("ids", Arrays.asList(ids));
        return jdbcDaoImp.executeSql(sql, map);
    }


    /** 根据名称获取幼儿园
     * @param name
     * @return
     */
    public SysKindergarten getSysKindergartenByName(String name) {
        QSysKindergarten qSysKindergarten = QSysKindergarten.sysKindergarten;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qSysKindergarten.name.eq(name).and(qSysKindergarten.status.ne(-1)));
                });
    }

    /** 根据orgId获取幼儿园
     * @param orgId
     * @return
     */
    public SysKindergarten getSysKindergartenByOrgId(String orgId) {
        QSysKindergarten qSysKindergarten = QSysKindergarten.sysKindergarten;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qSysKindergarten.orgId.eq(orgId));
                });
    }
}
