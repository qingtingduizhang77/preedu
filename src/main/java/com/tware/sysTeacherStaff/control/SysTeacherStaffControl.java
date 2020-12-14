package com.tware.sysTeacherStaff.control;

import com.alibaba.fastjson.JSON;
import com.querydsl.core.QueryResults;
import com.tware.common.utils.DateUtil;
import com.tware.common.utils.ExcelUtils;
import com.tware.common.utils.ImportExcelUtil;
import com.tware.common.utils.TypeNameUtil;
import com.tware.config.entity.QSpResults;
import com.tware.config.entity.SpResults;
import com.tware.config.service.SpResultsService;
import com.tware.kindergarten.entity.SysKindergarten;
import com.tware.kindergarten.service.SysKindergartenService;
import com.tware.log.annotation.ViLog;
import com.tware.sysTeacherStaff.entity.QSysTeacherStaff;
import com.tware.sysTeacherStaff.entity.SysTeacherStaff;
import com.tware.sysTeacherStaff.service.SysTeacherStaffService;
import com.tware.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swallow.framework.exception.SwallowException;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 教职工基本信息控制层
 * @author Guoyz
 * createTime   2020/9/7 16:27
 */
@RestController
@Api(tags = "教职工基本信息控制层")
@RequestMapping("/api/sysTeacherStaff")
public class SysTeacherStaffControl {
    private static final Logger log = LoggerFactory.getLogger(SysTeacherStaffControl.class);
    @Autowired
    private SysTeacherStaffService service;
    @Autowired
    private SysKindergartenService sysKindergartenService;
    @Autowired
    private SpResultsService spResultsService;
    @ApiModel(value="教职工基本信息查询对象")
    public static class QueryBean extends BasePageQueryBean {
        @Like
        @ApiModelProperty(value="部门",name="departId",example="")
        private String departId;
        @Like
        @ApiModelProperty(value="所属机构ID",name="orgId",example="")
        private String orgId;
        @Like
        @ApiModelProperty(value="教职工姓名",name="name",example="")
        private String name;

        @ApiModelProperty(value="证件类型",name="certType",example="")
        private String certType;

        @ApiModelProperty(value="证件号码",name="certNum",example="")
        private String certNum;

        @ApiModelProperty(value="主要职务",name="job",example="")
        private String job;

        @ApiModelProperty(value="修改时间",name="lastmodi",example="")
        private Date lastmodi;

        @ApiModelProperty(value="申请年度",name="year",example="")
        private String year;

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getDepartId() {
            return departId;
        }

        public void setDepartId(String departId) {
            this.departId = departId;
        }

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
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
     * 新增一个教职工基本信息对象
     * @param item
     * @return
     */
    @ApiOperation(value = "新增一个教职工基本信息对象")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @ViLog(operEvent = "新增一个教职工基本信息对象", operType =1)//日志记录
    @PutMapping()
    @SuppressWarnings("unchecked")
    public ApiResult<Map<String, Object>> insertItem(@RequestBody Map<String, Object> item) {
        try {
            SysTeacherStaff sysTeacherStaffNew = JSON.parseObject(JSON.toJSONString(item.get("sysTeacherStaff")), SysTeacherStaff.class);
            //先判断系统是否存在该教师
            SysTeacherStaff itemByCertNum = service.getItemByCertNum(sysTeacherStaffNew.getCertNum());
            if(itemByCertNum!=null){
                throw new SwallowException("教师已存在,不可重复新增！");
            }
            //判断回收站是否有该教师
            if(sysTeacherStaffNew.getFlag()==0){
                QueryBean queryBean = new QueryBean();
                queryBean.setCertNum(sysTeacherStaffNew.getCertNum());
                PageListData<SysTeacherStaff> pageListData = service.huishouList(queryBean);
                if(pageListData.getItems()!=null && pageListData.getItems().size()>0){
                    return ApiResult.fail(1001,"回收站已存在此学生！确定保存？");
                }
            }
            return ApiResult.success(service.insertItem(item));
        } catch (Exception e) {
            log.error("新增教职工基本信息出错:"+e.getMessage(),e);
            return ApiResult.fail("新增教职工基本信息出错:"+e.getMessage());
        }
    }

    /**
     * 删除教职工基本信息
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = " 删除教职工基本信息")
    @ViLog(operEvent = "删除教职工基本信息", operType =3)//日志记录
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "ids", value = "教职工基本信息IDs数组", required = true,allowMultiple=true, dataType = "Long")
    })
    public BaseApiResult deleteSysTeacherStaff(@RequestBody long []ids) {
        try {
            for(var id:ids)
                service.deleteItemById01(id);
            return BaseApiResult.successResult();
        } catch (Exception e) {
            log.error("删除教职工基本信息出错:"+e.getMessage(),e);
            return BaseApiResult.failResult(500,"删除教职工基本信息出错:"+e.getMessage());
        }
    }

    /**
     * 恢复教职工
     * @param ids
     * @return
     */
    @SuppressWarnings("unchecked")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "教职工基本信息Id", required = true, dataType = "long")
    })
    @PostMapping("/recoverItem")
    @ApiOperation(value = "恢复教职工")
    @ViLog(operEvent = "恢复教职工", operType =1)//日志记录
    public ApiResult recoverItem(@RequestBody long []ids){
        try {
            String msg = "";
            for (long id : ids) {
                try {
                service.recoverItem(id);
                } catch (Exception e){
                    msg += e.getMessage() + "教师\n";
                }
            }
            return ApiResult.success(StringUtils.isEmpty(msg) ? "还原成功！" : "系统已存在\n"+ msg);
        } catch (Exception e) {
            log.error("恢复教职工出错:"+e.getMessage(),e);
            return ApiResult.fail("恢复教职工出错:"+e.getMessage());
        }
    }

    /**
     * 修改教职工基本信息
     * @param item
     * @return
     */
    @PostMapping
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "修改教职工基本信息")
    @ViLog(operEvent = "修改教职工基本信息", operType =2)//日志记录
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    public ApiResult<Map<String, Object>> saveSysTeacherStaff(@RequestBody Map<String, Object> item){
        try {
            SysTeacherStaff sysTeacherStaffNew = JSON.parseObject(JSON.toJSONString(item.get("sysTeacherStaff")), SysTeacherStaff.class);
            //先判断系统是否存在该教师
            SysTeacherStaff itemByCertNum = service.getItemByCertNum(sysTeacherStaffNew.getCertNum());
            SysTeacherStaff itemById = service.getItemById(sysTeacherStaffNew.getId());

            if(itemByCertNum==null){
                //判断回收站是否有该教师
                if(sysTeacherStaffNew.getFlag()==0){
                    QueryBean queryBean = new QueryBean();
                    queryBean.setCertNum(sysTeacherStaffNew.getCertNum());
                    PageListData<SysTeacherStaff> pageListData = service.huishouList(queryBean);
                    if(pageListData.getItems()!=null && pageListData.getItems().size()>0){
                        return ApiResult.fail(1001,"回收站已存在此学生！确定保存？");
                    }
                }
            }else {
                if(!itemByCertNum.getCertNum().equals(itemById.getCertNum())){
                    throw new SwallowException("教师已存在,不可重复新增！");
                }
            }


//            if(itemByCertNum!=null && !itemByCertNum.getCertNum().equals(itemById.getCertNum())){//存在教职工且不是当前修改的教职工
//                throw new SwallowException("教师已存在,不可重复新增！");
//            }else {
//                //判断回收站是否有该教师
//                if(sysTeacherStaffNew.getFlag()==0){
//                    QueryBean queryBean = new QueryBean();
//                    queryBean.setCertNum(sysTeacherStaffNew.getCertNum());
//                    PageListData<SysTeacherStaff> pageListData = service.huishouList(queryBean);
//                    if(pageListData.getItems()!=null && pageListData.getItems().size()>0){
//                        return ApiResult.fail(1001,"回收站已存在此学生！确定保存？");
//                    }
//                }
//            }


            return ApiResult.success(service.updateItem(item));
        } catch (Exception e) {
            log.error("修改教职工基本信息出错:"+e.getMessage(),e);
            return ApiResult.fail("修改教职工基本信息出错:"+e.getMessage());
        }
    }

    /**
     * 通过查询bean进行分页查询数据
     * @param
     * @return
     */
    @PostMapping("listpage")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "通过查询bean进行分页查询数据")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)})
    public ApiResult<PageListData<SysTeacherStaff>> query(@RequestBody  QueryBean queryBean){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            QSysTeacherStaff sysTeacherStaff = QSysTeacherStaff.sysTeacherStaff;
            if(user.getType()==1 ){//区教育局管理员
                return ApiResult.success(service.getAllItemPageByQuerybean(queryBean,qu->qu.where(sysTeacherStaff.status.eq(0))));
            }
            if(user.getType()==2 ){//园区管理员
                queryBean.setOrgId(user.getOrgId());//只显示自己幼儿园的教职工名单
                return ApiResult.success(service.getAllItemPageByQuerybean(queryBean,qu->qu.where(sysTeacherStaff.status.eq(0))));
            }
            SysTeacherStaff item = service.getItem(query -> query.where(sysTeacherStaff.certNum.eq(user.getUsername())));
            if(item!=null && item.getOrgId()!=null){
                queryBean.setOrgId(item.getOrgId());//只显示自己幼儿园的教职工名单
                return ApiResult.success(service.getAllItemPageByQuerybean(queryBean,qu->qu.where(sysTeacherStaff.status.eq(0))));
            }
            return ApiResult.success(null);
        } catch (Exception e) {
            log.error("查询教职工基本信息出错:"+e.getMessage(),e);
            return ApiResult.fail("查询教职工基本信息出错:"+e.getMessage());
        }
    }

    /**
     * 根据id取教职工基本信息信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id取教职工基本信息信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "教职工基本信息Id", required = true, dataType = "long")
    })
    @GetMapping("{id}")
    @SuppressWarnings("unchecked")
    public ApiResult<Map<String, Object>> getItemById(@PathVariable long id){
        try {
            if(id==0){//返回本人信息
                User user = (User) SecurityUtils.getSubject().getPrincipal();
                SysTeacherStaff item = service.getItem(query -> query.where(QSysTeacherStaff.sysTeacherStaff.certNum.eq(user.getUsername())));
                if(item==null || item.getId()==null)return ApiResult.fail("没有查到个人信息，请重新登陆");
                id=item.getId();
            }
            return ApiResult.success(service.queryItemById(id));
        } catch (Exception e) {
            log.error("查询教职工基本信息出错:"+e.getMessage(),e);
            return ApiResult.fail("查询教职工基本信息出错:"+e.getMessage());
        }
    }

    /**
     * 重置教职工密码
     * @param ids
     * @return
     */
    @ApiOperation(value = "重置教职工密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) ,
            @ApiImplicitParam(name = "id", value = "教职工基本信息Id", required = true, dataType = "long")
    })
    @PostMapping("/resetPassword")
    @ViLog(operEvent = "重置教职工密码", operType =2)//日志记录
    @SuppressWarnings("unchecked")
    public ApiResult resetPassword(@RequestBody long []ids){
        try {
            for (long id : ids) {
                service.resetPassword(id);
            }
            return ApiResult.success("修改成功！");
        } catch (Exception e) {
            log.error("重置教职工密码出错:"+e.getMessage(),e);
            return ApiResult.fail("重置教职工密码出错:"+e.getMessage());
        }
    }

    /**
     * 批量导入教职工基本信息
     * @param file
     * @return
     */
    @ViLog(operEvent = "批量导入教职工基本信息", operType =1)//日志记录
    @RequestMapping(value = "/import")
    public BaseApiResult importExcel(@RequestParam(value = "file") MultipartFile file) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user.getType()==3){//是个教职工，所以只能上传本院的数据
            return ApiResult.fail("无权导入");
        }
        log.info("----------start----------importExcel---Teacher");
        if (file == null) {//没找到文件
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
            if (objList != null && objList.size()+1 > 3) {
                for (int i = 3; i < objList.size()+1; i++) {
                    List<Object> list = objList.get(i-1);
                    boolean ff = false;// 判断整行全部为空，是则不计
                    for(Object obj :list){
                        if (StringUtils.isNotEmpty(obj.toString())) {
                            ff = true;
                            break;
                        }
                    }
                    if (!ff) {
                        continue;
                    }
                    if (list.size() == 0 ||
                            StringUtils.isEmpty(list.get(0).toString()) ||
                            StringUtils.isEmpty(list.get(1).toString()) ||
                            StringUtils.isEmpty(list.get(2).toString()) ||
                            StringUtils.isEmpty(list.get(3).toString()) ||
                            StringUtils.isEmpty(list.get(4).toString()) ||
                            StringUtils.isEmpty(list.get(5).toString()) ||
                            StringUtils.isEmpty(list.get(6).toString()) ||
                            StringUtils.isEmpty(list.get(7).toString()) ||
                            StringUtils.isEmpty(list.get(8).toString()) ||
                            StringUtils.isEmpty(list.get(9).toString()) ||
                            StringUtils.isEmpty(list.get(10).toString()) ||
                            StringUtils.isEmpty(list.get(11).toString()) ||
                            StringUtils.isEmpty(list.get(12).toString()) ||
                            StringUtils.isEmpty(list.get(13).toString()) ||
                            StringUtils.isEmpty(list.get(14).toString()) ||
                            StringUtils.isEmpty(list.get(15).toString()) ||
                            StringUtils.isEmpty(list.get(16).toString()) ||
                            StringUtils.isEmpty(list.get(17).toString()) ||
                            StringUtils.isEmpty(list.get(18).toString())) {
                        msg += "第" + i + "行有空值！\n";
                        continue;
                    }
                    SysTeacherStaff item = new SysTeacherStaff();

                    item.setKindergarten(list.get(0).toString());//所属幼儿园
                    item.setName(list.get(1).toString());//教职工姓名
                    item.setCertType(TypeNameUtil.getTypeKeyByValue("zjlx", list.get(2).toString()));//证件类型
                    item.setCertNum(list.get(3).toString());//证件号码
                    item.setBirthay(DateUtil.parse("yyyy-MM-dd",list.get(4).toString()));//出生日期
                    item.setSex(TypeNameUtil.getTypeKeyByValue("xb", list.get(5).toString()));//性别
                    item.setSheBaoNum(list.get(6).toString());//在深社保卡电脑号
                    item.setContactWay(list.get(7).toString());//手机号码
                    item.setPlaceInfo(TypeNameUtil.getTypeKeyByValue("ethj",list.get(8).toString()));//户籍信息
                    item.setJuZhuZheng(TypeNameUtil.getTypeKeyByValue("sf", list.get(9).toString()));//是否办理居住证
                    item.setTotalWorkTime(DateUtil.parse("yyyy-MM-dd",list.get(10).toString()));//参加工作时间
                    item.setNowWorkTime(DateUtil.parse("yyyy-MM-dd",list.get(11).toString()));//进入本园时间
                    item.setJob(TypeNameUtil.getTypeKeyByValue("zyzw", list.get(12).toString()));//主要职务
                    item.setNianji(TypeNameUtil.getTypeKeyByValue("nj", list.get(13).toString()));//年级
                    item.setBanHao(TypeNameUtil.getTypeKeyByValue("bh", list.get(14).toString()));//班号
                    item.setTeacherCertName(TypeNameUtil.getTypeKeyByValue("dd_0037", list.get(15).toString()));//资格证名称
                    item.setTeacherCertNum(list.get(16).toString());//资格证编号
                    item.setJinTieYear(Integer.valueOf(list.get(17).toString()));//已领取保教人员从教津贴年限（年）
                    item.setSfTuiXiu(TypeNameUtil.getTypeKeyByValue("sf", list.get(18).toString()));//是否达到退休年龄

                    item.setOrgId(user.getOrgId());

                    if(user.getType()==1){
                        SysKindergarten sysKindergarten = sysKindergartenService.getSysKindergartenByName(list.get(0).toString());
                        if(sysKindergarten==null ||
                                sysKindergarten.getOrgId()==null ||
                                sysKindergarten.getOrgId().length()==0){//不存在该院校
                            msg += "第" + i + "行幼儿园不存在！\n" ;
                            continue;
                        }
                        item.setOrgId(sysKindergarten.getOrgId());
                    }
                    try{
                        //HashMap<String, Object> map = new HashMap<>();
                        //map.put("sysTeacherStaff",item);
                        //service.insertItem(map);
                        service.importExcel(item);
                    }catch (SwallowException e){
                        msg += "第" + i + "条此证件号码教职工已存在系统！\n";
                    }
                }
            } else {
                flag = false;
            }
        } catch (EncryptedDocumentException e){
            e.printStackTrace();
            return BaseApiResult.failResult(500, "导入失败！模板格式错误，请把文档重新另存为一份.xsl或.xslx");
        }catch (OLE2NotOfficeXmlFileException e){
            e.printStackTrace();
            return BaseApiResult.failResult(500, "导入失败！模板格式错误，请把文档重新另存为一份.xsl或.xslx");
        }catch (Exception e) {
            e.printStackTrace();
            return BaseApiResult.failResult(500, "导入失败！" + e.getMessage());
        }
        if (!flag) {
            return BaseApiResult.failResult(500, "导入失败，找不到要导入的数据！");
        }
        log.info("----------end----------importExcel---Teacher");
        return ApiResult.success(StringUtils.isEmpty(msg) ? "导入成功！" : msg);
    }


    /**
     * 批量导出教职工基本信息
     * @return
     */
    @ApiOperation(value = "批量导出教职工基本信息")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @RequestMapping(value = "/export")
    public void exportTeacherInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryBean bean) {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            bean.setPage(1);
            bean.setPageSize(10000);
            List<SysTeacherStaff> itemList = service.getAllItemByQuerybean(bean,qu->{
                qu.where(QSysTeacherStaff.sysTeacherStaff.status.eq(0));
                if (user.getType() == 2) {
                    qu.where(QSysTeacherStaff.sysTeacherStaff.orgId.eq(user.getOrgId()));
                }
                return qu;
            });
            HSSFWorkbook workbook = service.export(itemList);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "teacher" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
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
    }


    /**
     * 回收站列表
     * @param queryBean
     * @return
     */
    @PostMapping("/huishouList")
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "回收站列表")
    @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true)
    public ApiResult<PageListData<SysTeacherStaff>> huishouList(@RequestBody  QueryBean queryBean){
        try {
            QSysTeacherStaff sysTeacherStaff = QSysTeacherStaff.sysTeacherStaff;
//            if(queryBean.getCertNum()!=null && queryBean.getCertNum().length() >= 6){//证件号码模糊搜索
//                PageListData<SysTeacherStaff> allItemsByPage = service.getAllItemsByPage(
//                        query -> query.where(sysTeacherStaff.status.eq(-2).and(sysTeacherStaff.certNum.like("%"+queryBean.getCertNum()+"%")))
//                        , queryBean.getPage(), queryBean.getPageSize());
//                return ApiResult.success(allItemsByPage);
//            }
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if(user.getType()==1 ){//区教育局管理员
                //状态值为-2表示已经删除
                return ApiResult.success(service.huishouList(queryBean));
            }
            if(user.getType()==2 ){//幼儿园管理员
                queryBean.setOrgId(user.getOrgId());
                return ApiResult.success(service.huishouList(queryBean));
            }
            SysTeacherStaff item = service.getItem(query -> query.where(sysTeacherStaff.certNum.eq(user.getUsername())));
            if(item!=null && item.getOrgId()!=null){
                queryBean.setOrgId(item.getOrgId());
                return ApiResult.success(service.huishouList(queryBean));
            }
            return ApiResult.success(null);
        } catch (Exception e) {
            log.error("查看回收站列表出错:"+e.getMessage(),e);
            return ApiResult.fail("查看回收站列表出错:"+e.getMessage());
        }
    }

    /**
     * 申请结果列表
     * @param queryBean
     * @return
     */
    @PostMapping("/apply/results")
    @SuppressWarnings("unchecked")
    public ApiResult<PageListData<SysTeacherStaff>> queryApplyResults(@RequestBody QueryBean queryBean){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            //如果年份为空，则选择当前表中指定的月份
            String year = StringUtils.isEmpty(queryBean.getYear())? spResultsService.getApplyYear(): queryBean.getYear();
            queryBean.setYear(null);
            if(user.getType()==1 ){//区教育局管理员
                QueryResults<SysTeacherStaff> list =service.getApplyResults(queryBean.getOrgId(),queryBean.getName(),queryBean.getCertNum(),year,queryBean.getPage(), queryBean.getPageSize());
                return ApiResult.success(new PageListData<>(queryBean.getPageSize(),list.getTotal(),list.getResults()));
            }
            if(user.getType()==2 ){//园区管理员
                queryBean.setOrgId(user.getOrgId());//只显示自己幼儿园的教职工名单
                QueryResults<SysTeacherStaff> list =service.getApplyResults(queryBean.getOrgId(),queryBean.getName(),queryBean.getCertNum(),year,queryBean.getPage(), queryBean.getPageSize());
                return ApiResult.success(new PageListData<>(queryBean.getPageSize(),list.getTotal(),list.getResults()));
            }
            return ApiResult.success(null);
        } catch (Exception e) {
            log.error("查询申请结果出错:"+e.getMessage(),e);
            return ApiResult.fail("查询申请结果出错:"+e.getMessage());
        }
    }

    /**
     * 申请结果导出
     * @return
     */
    @ViLog(operEvent = "申请结果导出", operType =4)//日志记录
    @ApiOperation(value = "申请结果导出")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token", required = true) })
    @PostMapping(value = "/apply/results/export")
    public void exportExcelApplyResults(HttpServletRequest request, HttpServletResponse response, @RequestBody QueryBean queryBean) {
        log.info("----------start----------exportExcelApplyResults");
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if(user.getType()==2){queryBean.setOrgId(user.getOrgId());}
            //如果年份为空，则选择当前表中指定的月份
            String year = StringUtils.isEmpty(queryBean.getYear())? spResultsService.getApplyYear(): queryBean.getYear();
            queryBean.setYear(null);
            QueryResults<SysTeacherStaff> list =service.getApplyResults(queryBean.getOrgId(),queryBean.getName(),queryBean.getCertNum(),year,queryBean.getPage(), queryBean.getPageSize());
            List<SysTeacherStaff> teacherList = list.getResults();
            List<Map<String, Object>> rowlist = new ArrayList<>();
            if (!CollectionUtils.isEmpty(teacherList)) {
                for (SysTeacherStaff teacherStaff : teacherList) {
                    if (StringUtils.isNotEmpty(teacherStaff.getSpStatus())) {
                        teacherStaff.setSpStatus(teacherStaff.getSpStatus().equals("0")?"不通过":"通过");
                    }else {
                        teacherStaff.setSpStatus("无申请");
                    }
                    if (StringUtils.isNotEmpty(teacherStaff.getActStatus())) {
                        teacherStaff.setActStatus(teacherStaff.getActStatus().equals("0")?"不通过":"通过");
                    }else {
                        teacherStaff.setActStatus("无申请");
                    }
                    rowlist.add(ExcelUtils.getObjectToMap(teacherStaff));
                }
            }

            String[] titles = { "就职幼儿园", "教职工姓名", "证件号码", "审批结果", "实际结果", "备注"};
            String[] columnKey = { "kindergarten", "name", "certNum", "spStatus", "actStatus","remark"};

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); // 日期格式化
            request.setCharacterEncoding("utf-8");
            String fileName = "applyResults_" + sdf.format(new Date()) + ".xls";
            fileName = new String(fileName.getBytes("gbk"), "iso8859-1");
            HSSFWorkbook workbook = ExcelUtils.eqEcel(rowlist, titles, "申请结果", columnKey);
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
        log.info("----------end----------exportExcelApplyResults");
    }

    /**
     * 修正结果
     * @param item
     * @return
     */
    @ViLog(operEvent = "修正结果", operType =1)//日志记录
    @SuppressWarnings("unchecked")
    @PostMapping("/update/actStatus")
    @Transactional
    public BaseApiResult actStatus(@RequestBody SpResults item) {
        try {
            return new ApiResult(0,spResultsService.updItems(item));
        } catch (Exception e) {
            log.error("修正结果出错:"+e.getMessage(),e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ApiResult.fail("修正结果出错:"+e.getMessage());
        }
    }
}
