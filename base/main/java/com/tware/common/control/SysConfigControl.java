package com.tware.common.control;

import com.tware.config.entity.QSysConfig;
import com.tware.config.entity.SysConfig;
import com.tware.config.service.SysConfigService;
import com.tware.log.annotation.ViLog;
import com.tware.user.entity.User;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;

import java.util.List;

/**
 * 系统配置
 * @author zhengjc
 *
 */
@RestController
@Api(tags = "系统配置")
@RequestMapping("/api/config")
public class SysConfigControl {

    private static final Logger log = LoggerFactory.getLogger(SysConfigControl.class);

    @Autowired
    private SysConfigService service;

    /**
     * 配置列表
     * @return
     */
    @PostMapping("/list")
    @SuppressWarnings("unchecked")
    public ApiResult<List<SysConfig>> queryList(){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysConfig qSysConfig = QSysConfig.sysConfig;
            return ApiResult.success(service.getAllItems(
                    qu-> {
                        qu.where(qSysConfig.configKey.in("isCheckCode","webDataDic","dataDic","outDataDic"));
                        if (user.getType() != 999) {// 999：超级管理员
                            qu.where(qSysConfig.id.eq(0L));
                        }
                        return qu;
                    }));
        } catch (Exception e) {
            log.error("查询配置列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询配置列表出错:"+e.getMessage());
        }
    }

    /**
     * 修改系统配置
     * @param item
     * @return
     */
    @ViLog(operEvent = "修改系统配置", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    @Transactional
    public BaseApiResult saveConfig(@RequestBody List<SysConfig> item){
        try {
            for (SysConfig sysConfig : item) {
                if (sysConfig.getId() != null){
                    service.updateItem(sysConfig);
                }else{
                    service.insertItem(sysConfig);
                }
            }
            return ApiResult.success(null);
        } catch (Exception e) {
            log.error("修改系统配置出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("修改系统配置出错:"+e.getMessage());
        }
    }

    /**
     * 配置列表
     * @return
     */
    @PostMapping("/queryList")
    @SuppressWarnings("unchecked")
    public ApiResult<List<SysConfig>> queryList1(){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysConfig qSysConfig = QSysConfig.sysConfig;
            return ApiResult.success(service.getAllItems(
                    qu-> {
                        qu.where(qSysConfig.configKey.in("isSynJob","applyYear"));
                        if (user.getType() != 1) {// 1：教育局管理员
                            qu.where(qSysConfig.id.eq(0L));
                        }
                        return qu;
                    }));
        } catch (Exception e) {
            log.error("查询配置列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询配置列表出错:"+e.getMessage());
        }
    }
}
