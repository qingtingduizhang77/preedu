package com.tware.sysTeacherStaff.service;

import com.tware.sysTeacherStaff.entity.SysTeacherStaff;
import com.tware.sysTeacherStaff.entity.SysTeacherZhuanChu;
import com.tware.sysTeacherStaff.repository.SysTeacherZhuanChuRepository;
import com.tware.sysTeacherStaff.repository.SysTeacherZhuanRuRepository;
import com.tware.user.entity.User;
import com.tware.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.service.BaseService;

import javax.transaction.Transactional;

/**
 * @author Guoyz
 * createTime   2020/9/17 10:25
 */
@Service
public class SysTeacherZhuanChuService extends BaseService<SysTeacherZhuanChuRepository, SysTeacherZhuanChu> {
    @Autowired
    private SysTeacherStaffService sysTeacherStaffService;
    @Autowired
    private UserService userService;

    //转出
    @Transactional
    public void insertZhuanChu(SysTeacherZhuanChu sysTeacherZhuanChu) {
        long[] teacherIds = sysTeacherZhuanChu.getIds();
        for (long teacherId : teacherIds) {
            SysTeacherStaff teacherStaff = sysTeacherStaffService.getItemById(teacherId);
            if (teacherStaff != null){
                SysTeacherZhuanChu item = new SysTeacherZhuanChu();
                item.setCertNum(teacherStaff.getCertNum());
                item.setCertType(teacherStaff.getCertType());
                item.setDepartId(teacherStaff.getDepartId());
                item.setJob(teacherStaff.getJob());
                item.setOrgIdBefore(teacherStaff.getOrgId());
                item.setSex(teacherStaff.getSex());
                item.setName(teacherStaff.getName());
                item.setZhuanChuDate(sysTeacherZhuanChu.getZhuanChuDate());
                item.setStatus(1);//1：已转出 0：未转出
                super.insertItem(item);
                teacherStaff.setStatus(-1);//-1不显示该条数据
                sysTeacherStaffService.updateItem(teacherStaff);//不可在列表中查看该教师
                User user = userService.getItemById(teacherStaff.getUserId());
                user.setDisable(1);
                userService.updateItem(user);//教师账号不可登陆
            }
        }
    }

    //条件转出
    public void insertZhuanChu(String orgId, SysTeacherZhuanChu item) throws Exception {
        long[] ids = item.getIds();
        for (long id : ids) {
            SysTeacherStaff teacherStaff = sysTeacherStaffService.getItemById(id);
            if (teacherStaff==null)continue;
            if (!teacherStaff.getOrgId().equals(orgId)){
                throw new Exception("非本院管理员无法转出");
            }
        }
        insertZhuanChu(item);
    }


}
