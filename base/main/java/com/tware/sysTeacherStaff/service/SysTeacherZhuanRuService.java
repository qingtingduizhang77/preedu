package com.tware.sysTeacherStaff.service;

import com.tware.sysTeacherStaff.entity.SysTeacherStaff;
import com.tware.sysTeacherStaff.entity.SysTeacherZhuanChu;
import com.tware.sysTeacherStaff.entity.SysTeacherZhuanRu;
import com.tware.sysTeacherStaff.repository.SysTeacherZhuanRuRepository;
import com.tware.user.entity.User;
import com.tware.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import javax.transaction.Transactional;

/**
 * @author Guoyz
 * createTime   2020/9/17 14:52
 */
@Service
public class SysTeacherZhuanRuService extends BaseService<SysTeacherZhuanRuRepository, SysTeacherZhuanRu> {
    @Autowired
    private SysTeacherStaffService teacherStaffService;
    @Autowired
    private SysTeacherZhuanChuService zhuanChuService;
    @Autowired
    private UserService userService;
    @Transactional
    public void insertZhuanRu(SysTeacherZhuanRu item) throws Exception {

        if(item.getOrgIdNow()==null || item.getJob()==null ||
                item.getDepartId()==null || item.getZhuanRuDate()==null){
            throw new Exception("转入日期，转入幼儿园，部门，工作岗位都不能为空");}

        long[] ids = item.getIds();
        for (long id : ids) {
            SysTeacherZhuanChu zhuanchuItem = zhuanChuService.getItemById(id);
            if(zhuanchuItem==null || zhuanchuItem.getStatus()==0){continue;}
            SysTeacherZhuanRu sysTeacherZhuanRu = new SysTeacherZhuanRu();
            sysTeacherZhuanRu.setOrgIdBefore(zhuanchuItem.getOrgIdBefore());
            sysTeacherZhuanRu.setCertNum(zhuanchuItem.getCertNum());
            sysTeacherZhuanRu.setCertType(zhuanchuItem.getCertType());
            sysTeacherZhuanRu.setName(zhuanchuItem.getName());
            sysTeacherZhuanRu.setSex(zhuanchuItem.getSex());
            sysTeacherZhuanRu.setOrgIdNow(item.getOrgIdNow());
            sysTeacherZhuanRu.setDepartId(item.getDepartId());
            sysTeacherZhuanRu.setJob(item.getJob());
            sysTeacherZhuanRu.setZhuanRuDate(item.getZhuanRuDate());
            super.insertItem(sysTeacherZhuanRu);//添加转入记录

            //修改转出表状态
            zhuanchuItem.setStatus(0);
            zhuanChuService.updateItem(zhuanchuItem);

            //修改教师状态
            SysTeacherStaff teacherStaff = teacherStaffService.getItemByCertNum(zhuanchuItem.getCertNum());
            if(teacherStaff==null)continue;
            teacherStaff.setOrgId(item.getOrgIdNow());
            teacherStaff.setJob(item.getJob());
            teacherStaff.setDepartId(item.getDepartId());
            teacherStaff.setStatus(0);
            teacherStaffService.updateItem(teacherStaff);

            //修改教师账号账号状态
            User user = userService.getItemById(teacherStaff.getUserId());
            user.setOrgId(item.getOrgIdNow());
            user.setDisable(0);
            userService.updateItem(user);
        }
    }


}
