package com.tware.config.service;

import com.tware.config.entity.QSysExternalConfig;
import com.tware.config.entity.SysExternalConfig;
import com.tware.config.repository.SysExternalConfigRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class SysExternalConfigService extends BaseService<SysExternalConfigRepository, SysExternalConfig> {


    public SysExternalConfig getConfigInfo(String appId, String appSecret){
        QSysExternalConfig qSysExternalConfig = QSysExternalConfig.sysExternalConfig;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qSysExternalConfig.appId.eq(appId).and(qSysExternalConfig.appSecret.eq(appSecret)));
                });
    }
}
