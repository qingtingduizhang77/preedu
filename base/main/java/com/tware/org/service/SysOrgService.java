package com.tware.org.service;

import com.tware.kindergarten.entity.SysKindergarten;
import com.tware.org.entity.QSysOrg;
import com.tware.org.entity.SysOrg;
import com.tware.org.repository.SysOrgRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swallow.framework.service.BaseService;

@Service
public class SysOrgService extends BaseService<SysOrgRepository, SysOrg> {

    /** 根据机构ID获取机构
     * @param orgId
     * @return
     */
    public SysOrg getSysOrgByOrgId(String orgId) {
        QSysOrg qSysOrg = QSysOrg.sysOrg;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qSysOrg.orgId.eq(orgId));
                });
    }

    public SysOrg insertItem(SysKindergarten sysKindergarten, int type){
        SysOrg sysOrg = new SysOrg();
        sysOrg.setOrgId(sysKindergarten.getOrgId());
        sysOrg.setCode(sysKindergarten.getCode());
        sysOrg.setName(sysKindergarten.getName());
        sysOrg.setType(type);
        return super.insertItem(sysOrg);
    }
}
