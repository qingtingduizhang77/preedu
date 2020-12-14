package com.tware.config.service;

import com.tware.config.entity.QSysConfig;
import com.tware.config.entity.SysConfig;
import com.tware.config.repository.SysConfigRepository;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

@Service
public class SysConfigService extends BaseService<SysConfigRepository, SysConfig> {


    public SysConfig getConfig(String key){
        QSysConfig qSysConfig = QSysConfig.sysConfig;
        return getRepsitory()
                .findOneEntityByColumns(query -> {
                    return query.where(qSysConfig.configKey.eq(key));
                });
    }
}
