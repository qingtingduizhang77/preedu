package com.tware.sysTeacherStaff.control;

import com.tware.log.annotation.ViLog;
import com.tware.sysTeacherStaff.entity.QSysTeacherZhuanChu;
import com.tware.sysTeacherStaff.entity.SysTeacherZhuanChu;
import com.tware.sysTeacherStaff.entity.SysTeacherZhuanRu;
import com.tware.sysTeacherStaff.service.SysTeacherZhuanChuService;
import com.tware.sysTeacherStaff.service.SysTeacherZhuanRuService;
import com.tware.user.entity.User;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import java.util.Date;

/**
 * 教职工转入转出控制层
 * @author Guoyz
 * createTime   2020/9/17 10:26
 */
@RestController
@Api(tags = "教职工转入转出控制层")
@RequestMapping("/api/sysTeacherZhuan")
public class SysTeacherZhuanControl {
    private static final Logger log = LoggerFactory.getLogger(SysTeacherZhuanControl.class);
    @Autowired
    private SysTeacherZhuanChuService zhuanChuService;
    @Autowired
    private SysTeacherZhuanRuService zhuanRuService;

    @ApiModel(value="教职工转入转出查询对象")
    static class QueryBean extends BasePageQueryBean {

        @ApiModelProperty(value="部门",name="departId",example="")
        private String departId;

        @ApiModelProperty(value="原就职机构ID",name="orgIdBefore",example="")
        private String orgIdBefore;

        @ApiModelProperty(value="现就职机构ID",name="orgIdNow",example="")
        private String orgIdNow;

        @Like
        @ApiModelProperty(value="教职工姓名",name="name",example="")
        private String name;

        @ApiModelProperty(value="证件类型",name="certType",example="")
        private String certType;

        @ApiModelProperty(value="证件号码",name="certNum",example="")
        private String certNum;

        @ApiModelProperty(value="主要职务",name="job",example="")
        private String job;

        @ApiModelProperty(value="转出日期",name="ZhuanChuDate",example="")
        private Date zhuanChuDate;

        @ApiModelProperty(value="转入时间",name="ZhuanRuDate",example="")
        private Date zhuanRuDate;

        @ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

        public Date getZhuanRuDate() {
            return zhuanRuDate;
        }

        public void setZhuanRuDate(Date zhuanRuDate) {
            this.zhuanRuDate = zhuanRuDate;
        }

        public Date getZhuanChuDate() {
            return zhuanChuDate;
        }

        public void setZhuanChuDate(Date zhuanChuDate) {
            this.zhuanChuDate = zhuanChuDate;
        }

        public String getDepartId() {
            return departId;
        }

        public void setDepartId(String departId) {
            this.departId = departId;
        }

        public String getOrgIdBefore() {
            return orgIdBefore;
        }

        public void setOrgIdBefore(String orgIdBefore) {
            this.orgIdBefore = orgIdBefore;
        }

        public String getOrgIdNow() {
            return orgIdNow;
        }

        public void setOrgIdNow(String orgIdNow) {
            this.orgIdNow = orgIdNow;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCertType() {
            return certType;
        }

        public void setCertType(String certType) {
            this.certType = certType;
        }

        public String getCertNum() {
            return certNum;
        }

        public void setCertNum(String certNum) {
            this.certNum = certNum;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }
    }

    /**
     * 教职工转入
     * @param item
     * @return
     */
    @ApiOperation(value = "教职工转入")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "教职工转入", operType =1)//日志记录
    @PostMapping("/zhuanRu")
    @SuppressWarnings("unchecked")
    public ApiResult<String> insertZhuanRu(@RequestBody SysTeacherZhuanRu item) {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (user.getType() == 1) {
                zhuanRuService.insertZhuanRu(item);
            } else if (user.getType() == 2) {
                String orgId = user.getOrgId();
                if(!item.getOrgIdNow().equals(orgId)){return ApiResult.fail("园区管理员只能从本院转入到本院");}
                zhuanRuService.insertZhuanRu(item);
            } else {
                return ApiResult.fail("不是管理员，无权转入");
            }
            return ApiResult.success("转入成功");
        } catch (Exception e) {
            log.error("教职工转入出错:"+e.getMessage(),e);
            return ApiResult.fail("教职工转入出错:"+e.getMessage());
        }
    }
    /**
     * 教职工转出
     * @param
     * @return
     */
    @ApiOperation(value = "教职工转出")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "教职工转出", operType =1)//日志记录
    @PostMapping("/zhuanChu")
    @SuppressWarnings("unchecked")
    public ApiResult<String> insertZhuanChu(@RequestBody SysTeacherZhuanChu sysTeacherZhuanChu) {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (user.getType() == 1) {
                zhuanChuService.insertZhuanChu(sysTeacherZhuanChu);
            } else if (user.getType() == 2) {
                String orgId = user.getOrgId();
                zhuanChuService.insertZhuanChu(orgId, sysTeacherZhuanChu);
            } else {
                return ApiResult.fail("不是管理员，无权转出");
            }
            return ApiResult.success("转出成功");
        } catch (Exception e) {
            log.error("教职工转出出错:" + e.getMessage(), e);
            return ApiResult.fail("教职工转出出错:" + e.getMessage());
        }
    }
    /**
     * 删除教职工转出
     * @param ids
     * @return

    @DeleteMapping
    @ApiOperation(value = " 删除教职工转出")
    @ViLog(operEvent = "删除教职工转出", operType =3)//日志记录
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "教职工中转表IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    public BaseApiResult deleteSysTeacherZhuan(@RequestBody long []ids) {
        try {
            for(var id:ids)
                zhuanChuService.deleteItemById(id);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除教职工中转表出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除教职工中转表出错:"+e.getMessage());
        }
    }
     */

    /**
     * 教职工中转站列表
     * 通过查询bean进行分页查询数据
     * @param
     * @return
     */
    @PostMapping("/zhonZhuan/listpage")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "教职工中转站列表")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)})
    public ApiResult<PageListData<SysTeacherZhuanChu>> queryzhonZhuanList(@RequestBody QueryBean queryBean){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysTeacherZhuanChu sysTeacherZhuanChu = QSysTeacherZhuanChu.sysTeacherZhuanChu;
            if (user.getType() == 1) {
                return ApiResult.success(zhuanChuService.getAllItemPageByQuerybean(queryBean,qu->qu.where(sysTeacherZhuanChu.status.eq(1))));
            } else if (user.getType() == 2) {
                if(queryBean.getCertNum()==null){
                    String orgId = user.getOrgId();
                    queryBean.setOrgIdBefore(orgId);
                }
                return ApiResult.success(zhuanChuService.getAllItemPageByQuerybean(queryBean,qu->qu.where(sysTeacherZhuanChu.status.eq(1))));
            } else {
                return ApiResult.fail("不是管理员，无权查看教职工中转站列表");
            }
        } catch (Exception e) {
            log.error("查询教职工中转站列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询教职工中转站列表出错:"+e.getMessage());
        }
    }
    /**
     * 教职工转出记录列表
     * 通过查询bean进行分页查询数据
     * @param
     * @return
     */
    @PostMapping("/zhuanChu/listpage")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "教职工转出记录列表")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)})
    public ApiResult<PageListData<SysTeacherZhuanChu>> queryZhuanChuList(@RequestBody QueryBean queryBean){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (user.getType() == 1) {
                return ApiResult.success(zhuanChuService.getAllItemPageByQuerybean(queryBean));
            } else if (user.getType() == 2) {
                String orgId = user.getOrgId();
                queryBean.setOrgIdBefore(orgId);
                return ApiResult.success(zhuanChuService.getAllItemPageByQuerybean(queryBean));
            } else {
                return ApiResult.fail("不是管理员，无权查看转出记录列表");
            }
        } catch (Exception e) {
            log.error("查询教职工转出记录列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询教职工转出记录列表出错:"+e.getMessage());
        }
    }

    /**
     * 教职工转入记录列表
     * 通过查询bean进行分页查询数据
     * @param
     * @return
     */
    @PostMapping("/zhuanRu/listpage")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "教职工转入记录列表")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)})
    public ApiResult<PageListData<SysTeacherZhuanRu>> queryZhuanRuList(@RequestBody QueryBean queryBean) {
        try {

            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if (user.getType() == 1) {
                return ApiResult.success(zhuanRuService.getAllItemPageByQuerybean(queryBean));
            } else if (user.getType() == 2) {
                String orgId = user.getOrgId();
                queryBean.setOrgIdNow(orgId);
                return ApiResult.success(zhuanRuService.getAllItemPageByQuerybean(queryBean));
            } else {
                return ApiResult.fail("不是管理员，无权查看转入记录列表");
            }
        } catch (Exception e) {
            log.error("查询教职工转入记录列表出错:" + e.getMessage(), e);
            return ApiResult.fail("查询教职工转入记录列表出错:" + e.getMessage());
        }
    }

}
