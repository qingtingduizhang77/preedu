package com.tware.common.control;

import com.alibaba.fastjson.JSONObject;
import com.gexin.fastjson.JSON;
import com.tware.common.utils.HttpClientUtils;
import com.tware.config.entity.SysExternalConfig;
import com.tware.config.entity.vo.HeTongVo;
import com.tware.config.entity.vo.StudentVo;
import com.tware.config.entity.vo.TeacherVo;
import com.tware.config.service.SysExternalConfigService;
import com.tware.kindergarten.entity.SysKindergarten;
import com.tware.kindergarten.service.SysKindergartenService;
import com.tware.log.entity.SysExternalLog;
import com.tware.log.service.SysExternalLogService;
import com.tware.serviceClient.ServiceClientUtil;
import com.tware.student.entity.SysStudent;
import com.tware.student.service.SysStudentService;
import com.tware.sysTeacherStaff.entity.SysTeacherHeTong;
import com.tware.sysTeacherStaff.entity.SysTeacherStaff;
import com.tware.sysTeacherStaff.service.SysTeacherStaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;


/**
 * 外部调用的接口
 * @author zhengjc
 *
 */
@RestController
@Api(tags = "外部调用的接口")
@RequestMapping("/api/external")
public class SysExternalApiControl {

    private static final Logger log = LoggerFactory.getLogger(SysExternalApiControl.class);

    @Autowired
    private SysExternalConfigService externalConfigService;

    @Autowired
    private SysStudentService sysStudentService;

    @Autowired
    private SysKindergartenService kindergartenService;

    @Autowired
    private SysTeacherStaffService teacherStaffService;

    @Autowired
    private SysExternalLogService externalLogService;

    @Autowired
    private ServiceClientUtil serviceClientUtil;

    /**
     * 校验是否存在儿童信息
     * @param request
     * @param name
     * @param certNo
     * @return
     */
    @ApiOperation(value = "校验是否存在儿童信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "儿童姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "certNo", value = "儿童证件号码", required = true, dataType = "String")
    })
    @RequestMapping("/getStudentInfo")
    @SuppressWarnings("unchecked")
    public ApiResult<StudentVo> getStudentInfo(HttpServletRequest request,String name, String certNo){
        log.info("------start-------getStudentInfo");
        ApiResult apiResult = null;
        StudentVo vo = new StudentVo();
        SysExternalLog externalLog = new SysExternalLog();
        externalLog.setApiType("1001");
        try {
            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(certNo)) {
                log.info("------ name或certNo不能为空");
                return ApiResult.fail("参数错误！");
            }
            String authorization = request.getHeader("Authorization");
            log.info("authorization:" + authorization);
            externalLog.setCreated(new Date());
            if (StringUtils.isNotEmpty(authorization)) {
                externalLog.setReqDesc("Header="+authorization+";name="+name+";certNo="+certNo);
                String appConfig = new String(Base64.getDecoder().decode(authorization.split(" ")[1]));
                if(appConfig.split(":").length<2){
                    log.info("appConfig:" + appConfig);
                    return ApiResult.fail("参数错误！");
                }else {
                    String appId = appConfig.split(":")[0];
                    String appSecret = appConfig.split(":")[1];
                    SysExternalConfig sysExternalConfig = externalConfigService.getConfigInfo(appId, appSecret);
                    externalLog.setAppId(appId);
                    if (sysExternalConfig == null) {
                        log.info("------ 没有appId,appSecret配置");
                        return ApiResult.fail("您不是合法用户！");
                    }else {
                        SysStudent sysStudent = sysStudentService.getStudentInfo(name, certNo);
                        if (sysStudent != null) {
                            SysKindergarten kindergarten = kindergartenService.getSysKindergartenByOrgId(sysStudent.getOrgId());
                            BeanUtils.copyProperties(sysStudent, vo);
                            vo.setKindergartenName(kindergarten.getName());
                            vo.setKindergartenCode(kindergarten.getCode());
                        }
                    }
                }
            }
            apiResult = ApiResult.success(vo);
            externalLog.setResDesc(JSON.toJSONString(apiResult));
        } catch (Exception e) {
            log.error("查询儿童信息出错:"+e.getMessage(),e);
            externalLog.setStatus(1);//系统异常
            externalLog.setMessage(e.getMessage());//错误信息
            apiResult = ApiResult.fail("查询儿童信息出错:"+e.getMessage());
            externalLog.setResDesc(JSON.toJSONString(apiResult));
            externalLogService.insertItem(externalLog);
            return apiResult;
        }
        externalLogService.insertItem(externalLog);
        log.info("------end-------getStudentInfo");
        return apiResult;
    }


    /**
     * 校验是否存在教职工信息
     * @param request
     * @param name
     * @param certNo
     * @return
     */
    @ApiOperation(value = "校验是否存在教职工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "教职工姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "certNo", value = "教职工证件号码", required = true, dataType = "String")
    })
    @RequestMapping("/getTeacherInfo")
    @SuppressWarnings("unchecked")
    public ApiResult<TeacherVo> getTeacherInfo(HttpServletRequest request,String name, String certNo){
        log.info("------start-------getTeacherInfo");
        ApiResult apiResult = null;
        TeacherVo vo = new TeacherVo();
        SysExternalLog externalLog = new SysExternalLog();
        externalLog.setApiType("1002");
        try {
            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(certNo)) {
                log.info("------ name或certNo不能为空");
                return ApiResult.fail("参数错误！");
            }
            String authorization = request.getHeader("Authorization");
            log.info("authorization:" + authorization);
            externalLog.setCreated(new Date());
            if (StringUtils.isNotEmpty(authorization)) {
                externalLog.setReqDesc("Header="+authorization+";name="+name+";certNo="+certNo);
                String appConfig = new String(Base64.getDecoder().decode(authorization.split(" ")[1]));
                if(appConfig.split(":").length<2){
                    log.info("appConfig:" + appConfig);
                    return ApiResult.fail("参数错误！");
                }else {
                    String appId = appConfig.split(":")[0];
                    String appSecret = appConfig.split(":")[1];
                    SysExternalConfig sysExternalConfig = externalConfigService.getConfigInfo(appId, appSecret);
                    externalLog.setAppId(appId);
                    if (sysExternalConfig == null) {
                        log.info("------ 没有appId,appSecret配置");
                        return ApiResult.fail("您不是合法用户！");
                    }else {
                        SysTeacherStaff teacherStaff = teacherStaffService.getTeacherInfo(name, certNo);
                        if (teacherStaff != null) {
                            SysKindergarten kindergarten = kindergartenService.getSysKindergartenByOrgId(teacherStaff.getOrgId());
                            BeanUtils.copyProperties(teacherStaff, vo);
                            vo.setKindergartenName(kindergarten.getName());
                            vo.setKindergartenCode(kindergarten.getCode());
                            List<HeTongVo> heTongVoList = new ArrayList<>();
                            for (SysTeacherHeTong teacherHeTong : teacherStaffService.getHeTongList(teacherStaff.getCertNum())){
                                HeTongVo heTongVo = new HeTongVo();
                                BeanUtils.copyProperties(teacherHeTong, heTongVo);
                                heTongVoList.add(heTongVo);
                            }
                            vo.setHeTongList(heTongVoList);
                        }
                    }
                }
            }
            apiResult = ApiResult.success(vo);
            externalLog.setResDesc(JSON.toJSONString(apiResult));
        } catch (Exception e) {
            log.error("查询教职工信息出错:"+e.getMessage(),e);
            externalLog.setStatus(1);//系统异常
            externalLog.setMessage(e.getMessage());//错误信息
            apiResult = ApiResult.fail("查询教职工信息出错:"+e.getMessage());
            externalLog.setResDesc(JSON.toJSONString(apiResult));
            externalLogService.insertItem(externalLog);
            return apiResult;
        }
        externalLogService.insertItem(externalLog);
        log.info("------end-------getTeacherInfo");
        return apiResult;
    }

    /**
     *  初始化数据
     * @return
     */
    @ApiOperation(value = "初始化数据")
    @RequestMapping("/initData")
    @SuppressWarnings("unchecked")
    public ApiResult<String> initData(@RequestParam(value = "startTime", required = true) String startTime,
                                      @RequestParam(value = "endTime", required = true) String endTime) {
        try {

            String param="{\"startTime\":'"+startTime+"',\"endTime\":'"+endTime+"'}";

            String re=serviceClientUtil.csExtend(param);

            return ApiResult.success(re);
        } catch (Exception e) {
            log.error("初始化数据出错:"+e.getMessage(),e);
            return ApiResult.fail("初始化数据出错:"+e.getMessage());
        }
    }
    /**
     *  儿童成长补贴审批结果同步
     * @return
     */
    @ApiOperation(value = "儿童成长补贴审批结果同步")
    @RequestMapping("/getStudentBuTie")
    @SuppressWarnings("unchecked")
    public ApiResult<String> getStudentBuTie(@RequestBody String json) {
        try{
            return ApiResult.success(HttpClientUtils.doPost(HttpClientUtils.url, JSONObject.parseObject(JSONObject.parseObject(json).getString("json"))));
        }catch (Exception e){
            e.printStackTrace();
            return ApiResult.fail("参数错误或系统异常！" + e.getMessage());
        }
    }
}
