package com.tware.kindergarten.control;

import com.tware.common.utils.DateUtil;
import com.tware.common.utils.ExcelUtils;
import com.tware.common.utils.ImportExcelUtil;
import com.tware.common.utils.TypeNameUtil;
import com.tware.kindergarten.entity.QSysKindergarten;
import com.tware.kindergarten.entity.SysKindergarten;
import com.tware.kindergarten.service.SysKindergartenService;
import com.tware.log.annotation.ViLog;
import com.tware.org.entity.SysOrg;
import com.tware.org.service.SysOrgService;
import com.tware.user.entity.User;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swallow.framework.exception.SwallowConstrainException;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 幼儿园
 * @author zhengjc
 *
 */
@RestController
@Api(tags = "幼儿园")
@RequestMapping("/api/kindergarten")
public class SysKindergartenControl {

    private static final Logger log = LoggerFactory.getLogger(SysKindergartenControl.class);

    @Autowired
    private SysKindergartenService service;

    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 查询对象
     *
     */
    @ApiModel(value="幼儿园查询对象")
    static class QueryBean extends BasePageQueryBean {

        @ApiModelProperty(value="名称",name="name",example="")
        @Like
        private String name; //名称

        @ApiModelProperty(value="所属街道",name="streetCode",example="")
        private String streetCode;// 所属街道

        @ApiModelProperty(value="所属片区",name="areaCode",example="")
        private String areaCode;// 所属片区

        @ApiModelProperty(value="办学性质",name="schoolNature",example="")
        private String schoolNature;// 办学性质

        @ApiModelProperty(value="幼儿园状态",name="status",example="")
        private Integer status;// 幼儿园状态

        private Date lastmodi;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStreetCode() {
            return streetCode;
        }

        public void setStreetCode(String streetCode) {
            this.streetCode = streetCode;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getSchoolNature() {
            return schoolNature;
        }

        public void setSchoolNature(String schoolNature) {
            this.schoolNature = schoolNature;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }
    }

    /**
     * 查询对象
     *
     */
    @ApiModel(value="幼儿园查询对象")
    static class QueryBeanNoPage  extends BaseQueryBean {

        @ApiModelProperty(value="名称",name="name",example="")
        @Like
        private String name; //名称

        @ApiModelProperty(value="所属街道",name="streetCode",example="")
        private String streetCode;// 所属街道

        @ApiModelProperty(value="所属片区",name="areaCode",example="")
        private String areaCode;// 所属片区

        @ApiModelProperty(value="办学性质",name="schoolNature",example="")
        private String schoolNature;// 办学性质

        @ApiModelProperty(value="幼儿园状态",name="status",example="")
        private Integer status;// 幼儿园状态

        private Date lastmodi;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStreetCode() {
            return streetCode;
        }

        public void setStreetCode(String streetCode) {
            this.streetCode = streetCode;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }

        public String getSchoolNature() {
            return schoolNature;
        }

        public void setSchoolNature(String schoolNature) {
            this.schoolNature = schoolNature;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Date getLastmodi() {
            return lastmodi;
        }

        public void setLastmodi(Date lastmodi) {
            this.lastmodi = lastmodi;
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param query
     * @return
     */
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<SysKindergarten>> query(@RequestBody QueryBean query){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysKindergarten qSysKindergarten = QSysKindergarten.sysKindergarten;
            return ApiResult.success(service.getAllItemPageByQuerybean(query,
                    qu-> {
                        qu.where(qSysKindergarten.status.ne(-1));
                        if (user.getType() != 1) {
                            qu.where(qSysKindergarten.orgId.eq(user.getOrgId()));
                        }
                        return qu;
                    }));
        } catch (Exception e) {
            log.error("查询幼儿园对象出错:"+e.getMessage(),e);
            return ApiResult.fail("查询幼儿园对象出错:"+e.getMessage());
        }
    }

    @ApiOperation(value = "根据id取幼儿园信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "幼儿园id", required = true, dataType = "long")
    })
    @RequestMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<SysKindergarten> getItemById(@PathVariable long id){
        try {
            return ApiResult.success(service.getItemById(id));
        } catch (Exception e) {
            log.error("查询幼儿园信息出错:"+e.getMessage(),e);
            return ApiResult.fail("查询幼儿园信息出错:"+e.getMessage());
        }
    }


    /**
     * 删除幼儿园
     * @param ids
     * @return
     */
    @ViLog(operEvent = "删除幼儿园", operType = 3)//日志记录
    @ApiOperation(value = "删除幼儿园")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "幼儿园IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @DeleteMapping
    public BaseApiResult delete(@RequestBody Long []ids) {
        try {
                service.updateStatus(ids,-1);// 软删除
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除幼儿园出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除幼儿园出错:"+e.getMessage());
        }
    }


    /**
     * 新增幼儿园对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "新增幼儿园对象", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PutMapping()
    @Transactional
    public ApiResult<SysKindergarten> saveKindergarten(@RequestBody SysKindergarten item) {
        try {
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增幼儿园对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("新增幼儿园出错:"+e.getMessage());
        }
    }

    /**
     * 修改幼儿园对象
     * @param item
     * @return
     */
    @ViLog(operEvent = "修改幼儿园对象", operType =2)//日志记录
    @PostMapping
    @SuppressWarnings("unchecked")
    @Transactional
    public ApiResult<SysKindergarten> saveSysKindergarten(@RequestBody SysKindergarten item){
        try {
                item = service.updateItem(item);
                SysOrg sysOrg = sysOrgService.getSysOrgByOrgId(item.getOrgId());
                sysOrg.setName(item.getName());
                sysOrgService.updateItem(sysOrg);
            return ApiResult.success(item);
        } catch (Exception e) {
            log.error("修改幼儿园对象出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("修改幼儿园对象出错:"+e.getMessage());
        }
    }


    /**
     * 开启幼儿园
     * @param ids
     * @return
     */
    @ViLog(operEvent = "开启幼儿园", operType =2)//日志记录
    @ApiOperation(value = "开启幼儿园")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "幼儿园IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @RequestMapping("/open")
    @Transactional
    public BaseApiResult openKindergarten(@RequestBody Long []ids) {
        try {
                service.updateStatus(ids,0);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("开启幼儿园出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"开启幼儿园出错:"+e.getMessage());
        }
    }

    /**
     * 关闭幼儿园
     * @param ids
     * @return
     */
    @ViLog(operEvent = "关闭幼儿园", operType =2)//日志记录
    @ApiOperation(value = "关闭幼儿园")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "幼儿园IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    @RequestMapping("/close")
    @Transactional
    public BaseApiResult closeKindergarten(@RequestBody Long []ids) {
        try {
                service.updateStatus(ids,1);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("关闭幼儿园出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500,"关闭幼儿园出错:"+e.getMessage());
        }
    }


    /**
     * 批量导入幼儿园信息
     * @param file
     * @return
     */
    @ViLog(operEvent = "批量导入幼儿园信息", operType =1)//日志记录
    @RequestMapping(value = "/import")
    @Transactional
    public BaseApiResult importExcel(@RequestParam(value = "file") MultipartFile file) {
        log.info("----------start----------importExcel---kindergarten");
        if (file == null) {
            return BaseApiResult.failResult(500, "请重新上传文件！");
        }
        boolean flag = true;
        String msg = "";
        try {
            // 读取内容
            InputStream in = file.getInputStream();
            Map<String, Object> objMap = new ImportExcelUtil().getMapByExcel(in, file.getOriginalFilename(), false);

            @SuppressWarnings("unchecked")
            List<ArrayList<Object>> objList = (List<ArrayList<Object>>) objMap.get("list");
            if (objList != null && objList.size() > 1) {
                for (int i = 1; i < objList.size(); i++) {
                    List<Object> list = objList.get(i);
                    if (list.size() == 0 ||
                            StringUtils.isEmpty(list.get(0).toString()) ||
                            StringUtils.isEmpty(list.get(1).toString()) ||
                            StringUtils.isEmpty(list.get(3).toString()) ||
                            StringUtils.isEmpty(list.get(4).toString()) ||
                            StringUtils.isEmpty(list.get(5).toString())) {
                        msg += "第" + i + "条有空值！\n";
                        continue;
                    }

                    SysKindergarten item = new SysKindergarten();
                    item.setCode(list.get(0).toString());//编码
                    item.setStreetCode(TypeNameUtil.getTypeKeyByValue("yryssjd", list.get(1).toString()));//所属街道
                    item.setAreaCode(TypeNameUtil.getTypeKeyByValue("yeysspq", list.get(2).toString()));//所属片区
                    item.setName(list.get(3).toString());//幼儿园名称
                    item.setAddress(list.get(4).toString());
                    item.setSchoolNature(TypeNameUtil.getTypeKeyByValue("bxxz", list.get(5).toString()));//办学性质
                    item.setSetupTime(DateUtil.parse("yyyy-MM-dd",list.get(6).toString()));
                    try{
                        service.insertItem(item);
                    }catch (SwallowConstrainException e){
                        msg += "第" + i + "数据异常！\n";
                    }
                }
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return BaseApiResult.failResult(500, "导入失败！" + e.getMessage());
        }
        if (!flag) {
            return BaseApiResult.failResult(500, "导入失败，找不到要导入的数据！");
        }

        log.info("----------end----------importExcel---kindergarten");
        return ApiResult.success(StringUtils.isEmpty(msg) ? "导入成功！" : msg);
    }


    /**
     * 幼儿园信息导出
     * @return
     */
    @ViLog(operEvent = "幼儿园信息导出", operType =4)//日志记录
    @ApiOperation(value = "幼儿园信息导出")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryBeanNoPage queryBean) {
        log.info("----------start----------exportExcel---kindergarten");
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysKindergarten qSysKindergarten = QSysKindergarten.sysKindergarten;
            List<SysKindergarten> kindergartens = service.getAllItemByQuerybean(queryBean,
                    qu-> {
                        qu.where(qSysKindergarten.status.ne(-1));
                        if (user.getType() != 1) {
                            qu.where(qSysKindergarten.orgId.eq(user.getOrgId()));
                        }
                        return qu;
                    });
            List<Map<String, Object>> rowlist = new ArrayList<>();
            for (SysKindergarten kindergarten : kindergartens){
                kindergarten.setAreaCodeName(TypeNameUtil.getTypeName("yeysspq", kindergarten.getAreaCode()));
                kindergarten.setStreetCodeName(TypeNameUtil.getTypeName("yryssjd", kindergarten.getStreetCode()));
                kindergarten.setSchoolNatureName(TypeNameUtil.getTypeName("bxxz", kindergarten.getSchoolNature()));
                rowlist.add(ExcelUtils.getObjectToMap(kindergarten));
            }

            String[] titles = { "幼儿园编码", "幼儿园名称", "幼儿园所属街道", "幼儿园所属片区", "办学性质"};
            String[] columnKey = { "code", "name", "streetCodeName", "areaCodeName", "schoolNatureName"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "kindergarten_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "幼儿园", columnKey);
            response.reset();
            response.setContentType("contentType=application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Connection", "close");
            workbook.write(response.getOutputStream());
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("----------end----------exportExcel---kindergarten");
    }


    /**
     * 幼儿园列表
     * @param query
     * @return
     */
    @PostMapping("/list")
    @SuppressWarnings("unchecked")
    public ApiResult<List<SysKindergarten>> queryList(@RequestBody QueryBean query){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysKindergarten qSysKindergarten = QSysKindergarten.sysKindergarten;
            return ApiResult.success(service.getAllItemByQuerybean(query,
                    qu-> {
                        qu.where(qSysKindergarten.status.ne(-1));
                        if (user.getType() != 1) {
                            qu.where(qSysKindergarten.orgId.eq(user.getOrgId()));
                        }
                        return qu;
                    }));
        } catch (Exception e) {
            log.error("查询幼儿园列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查询幼儿园列表出错:"+e.getMessage());
        }
    }
}
