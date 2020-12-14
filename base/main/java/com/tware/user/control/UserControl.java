package com.tware.user.control;


import java.io.InputStream;
import java.util.*;

import com.tware.org.entity.SysOrg;
import com.tware.org.service.SysOrgService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tware.common.utils.ImportExcelUtil;
import com.tware.common.utils.PasswordHelper;
import com.tware.log.annotation.ViLog;
import com.tware.role.entity.Role;
import com.tware.user.entity.User;
import com.tware.user.entity.dto.UserInfo;
import com.tware.user.entity.vo.AllocateUserAreaVo;
import com.tware.user.entity.vo.AllocateUserRoleVo;
import com.tware.user.entity.vo.UserVo;
import com.tware.user.service.UserManagerService;
import com.tware.user.service.UserService;

import io.swagger.annotations.Api;
import swallow.framework.exception.SwallowConstrainException;
import swallow.framework.jpaquery.repository.annotations.Like;
import swallow.framework.web.ApiResult;
import swallow.framework.web.BaseApiResult;
import swallow.framework.web.BasePageQueryBean;
import swallow.framework.web.PageListData;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户数据访问API接口
 * @author zhengjc
 *
 */
@Api(tags = "系统用户数据访问API接口")
@RestController
@RequestMapping("/api/user")
public class UserControl {
	
	private static final Logger log = LoggerFactory.getLogger(UserControl.class);

	@Autowired
	private UserService service;
	@Autowired
	private UserManagerService userManagerService;
	@Autowired
	private SysOrgService sysOrgService;
	

	/**
	 * 查询对象
	 * @author aohanhe
	 *
	 */
	static class QueryBean extends BasePageQueryBean{

		// 姓名
		@Like
		private String name;

		// 用户名
		@Like
		private String username;

		// 部门Id
		private Long departmentId;

		// 是否禁用
		private Integer disable;


		private Date lastmodi;

		public Date getLastmodi() {
			return lastmodi;
		}

		public void setLastmodi(Date lastmodi) {
			this.lastmodi = lastmodi;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		public String getUsername() {
			return username;
		}

		public Integer getDisable() {
			return disable;
		}

		public void setDisable(Integer disable) {
			this.disable = disable;
		}

		public Long getDepartmentId() {
			return departmentId;
		}

		public void setDepartmentId(Long departmentId) {
			this.departmentId = departmentId;
		}
	}
	
	
	/**
	 * 管理员修改用户密码
	 * @param pwd
	 * @param id    系统用户ID
	 * @return
	 */
	@ViLog(operEvent = "管理员修改用户密码", operType =2)//日志记录
	@RequestMapping("udpUserpwd")
	@RequiresPermissions(value={"001001001","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public ApiResult<User> udpUserpwd(String pwd,long id){
		try {
			User user = service.getItemById(id);
			if(user!=null)
			{
				user.setPassword(pwd);
			    service.updateItem(user);
			}
			return ApiResult.success("修改成功！");
		} catch (Exception e) {
			log.error("管理员修改用户密码出错:"+e.getMessage(),e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ApiResult.fail("管理员修改用户密码出错:"+e.getMessage());
		}
	}
	
	
	
	/**
	 * 新增一个新的用户对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "新增一个新的用户对象", operType =1)//日志记录
	@SuppressWarnings("unchecked")
	@PutMapping()
	@RequiresPermissions(value={"001001001","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public ApiResult<User> saveNewUser(@RequestBody User item) {
		    try {
			String password = item.getPassword();
			password = PasswordHelper.encryptPassword(password);
			item.setPassword(password);
			
			User user=service.insertItem(item);
			user.setPassword(null);
			
			return ApiResult.success(user);
		    } catch (Exception e) {
				log.error("新增用户出错::"+e.getMessage(),e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return ApiResult.fail("新增用户出错:"+e.getMessage());
			}
	}



	/**
	 * 批量导入新的用户对象
	 * @author Guoyingzhao
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/excel")
	@ViLog(operEvent = "批量导入新的用户对象", operType =1)//日志记录
	@RequiresPermissions(value={"001001011","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public BaseApiResult UserImportExcel(@RequestParam(value = "file") MultipartFile file) {
		log.info("----------start----------UserImportExcel");
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
							StringUtils.isEmpty(list.get(2).toString()) ||
							StringUtils.isEmpty(list.get(3).toString()) ) {
						msg = StringUtils.isEmpty(msg) ? "第" + i : msg + "、" + i;
						continue;
					}
					User item = new User();
					item.setUsername(list.get(0).toString());//用户名
					String password = PasswordHelper.encryptPassword(list.get(1).toString());
					item.setPassword(password);//密码
					item.setName(list.get(2).toString());//姓名
					item.setDisable(Integer.parseInt(list.get(5).toString()));//是否禁用
					try{
						service.insertItem(item);
					}catch (SwallowConstrainException e){
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						msg = StringUtils.isEmpty(msg) ? "第" + i : msg + "、" + i;
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
		if(SwallowConstrainException.class!=null){
			msg = StringUtils.isEmpty(msg) ? "" : msg + "条的数据导入失败！\t失败原因：用户名(username)和手机号码(phone)字段不能与其他用户重复！";
			return BaseApiResult.failResult(500, "导入有误，"+msg);
		}

		msg = StringUtils.isEmpty(msg) ? "" : msg + "条数据添加失败！";
		log.info("----------end----------importDeviceInfo");
		return ApiResult.success("导入成功！" + msg);
	}
	
	
	
	
	
	/**
	 * 删除用户对象
	 * @return
	 */
	@ViLog(operEvent = "删除用户对象", operType =3)//日志记录
	@DeleteMapping
	@RequiresPermissions(value={"001001002","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public BaseApiResult deleteUser(@RequestBody long []ids) {
		try {
			for(var id:ids)
			{
				User user=service.getItemById(id);
				service.deleteItemById(id);
			}
				
			return BaseApiResult.successResult();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return BaseApiResult.failResult(500, "删除用户对象失败！" + e.getMessage());
		}
	}
	
	/**
	 * 修改用户对象
	 * @param item
	 * @return
	 */
	@ViLog(operEvent = "修改用户对象", operType =2)//日志记录
	@PostMapping
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001001003","SUPERADMIN"}, logical=Logical.OR)
	@Transactional
	public ApiResult<User> saveUser(@RequestBody UserVo item){
		User user = service.getItemById(item.getId());
		try {
			///String pwd=user.getPassword();
			
			if(null==user) {
				return ApiResult.fail("要修改的用户不存在");
			}
			BeanUtils.copyProperties(item,user);
			user = service.updateItem(user);
	   } catch (Exception e) {
		   e.printStackTrace();
		   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		   return ApiResult.fail(500, "修改用户对象失败！" + e.getMessage());//BaseApiResult.failResult(500, "修改用户对象失败！" + e.getMessage());
	    }
//		user.setPassword(null);
		return ApiResult.success(user);
	}
	
	/**
	 * 通过查询bean进行分页查询数据
	 * @param query
	 * @return
	 */
	@PostMapping("listpage")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001001004","SUPERADMIN"},logical= Logical.OR)
	public ApiResult<PageListData<User>> query(@RequestBody  QueryBean query){
			PageListData<User> userPageListData = service.getAllItemPageByQuerybean(query);
			for(User user:userPageListData.getItems()) {
				user.setPassword(null);
			}
			return ApiResult.success(userPageListData);
	}

	@RequestMapping("{id}")
	@SuppressWarnings("unchecked")
	@RequiresPermissions(value={"001001005","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<User> getItemById(@PathVariable long id){
			return ApiResult.success(service.getItemById(id));
	}

	@RequestMapping("userinfo")
	public ApiResult<UserInfo> getUserInfo() {
			return ApiResult.success(userManagerService.getUserInfoByUserId());
	}

	/** 修改用户密码
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	@ViLog(operEvent = "修改用户密码", operType =2)//日志记录
	@RequestMapping("updatePassword")
	@Transactional
	public ApiResult updatePassword(String oldPassword,String newPassword) {
	     try {
	     	service.updatePassword(oldPassword,newPassword);

			return ApiResult.success("修改密码成功");
	     } catch (Exception e) {
			   e.printStackTrace();
			   TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			   return ApiResult.fail(500, "修改用户密码失败！" + e.getMessage());//BaseApiResult.failResult(500, "修改用户对象失败！" + e.getMessage());
		}
	}

	/** 通过用户Id获取用户所有的角色
	 * @param userId
	 * @return
	 */
	@RequestMapping("getAllRoleByUserId")
	@RequiresPermissions(value={"001001006","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult<List<Role>> getAllRoleByUserId(long userId) {
			return ApiResult.success(userManagerService.getAllRoleByUserId(userId));
	}

	/** 用户角色分配
	 * @return
	 */
	@ViLog(operEvent = "用户角色分配", operType =1)//日志记录
	@RequestMapping("allocateUserRole")
	@RequiresPermissions(value={"001001007","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult allocateUserRole(@RequestBody AllocateUserRoleVo allocateUserRoleVo) {
			userManagerService.allocateUserRole(allocateUserRoleVo.getUserId(),
					allocateUserRoleVo.getReadyGiveUpRoleIds(),
					allocateUserRoleVo.getReadyGiveRoleIds());
			return ApiResult.success("分配角色成功");
	}

	/** 用户区域分配
	 * @return
	 */
	@ViLog(operEvent = "用户区域分配", operType =1)//日志记录
	@RequestMapping("allocateUserArea")
	@RequiresPermissions(value={"001001009","SUPERADMIN"}, logical=Logical.OR)
	public ApiResult allocateUserArea(@RequestBody AllocateUserAreaVo allocateUserAreaVo) {
		userManagerService.allocateUserArea(allocateUserAreaVo.getUserId(),
				allocateUserAreaVo.getReadyGiveUpAreaIds(),
				allocateUserAreaVo.getReadyGiveAreaIds());
		return ApiResult.success("分配区域成功");
	}

	/**
	 * 获取用户信息
	 * @return
	 */
	@RequestMapping("getAccountInfo")
	public ApiResult<Map<String,Object>> getUserInfo(HttpServletRequest request){
		try {
			User user = (User) SecurityUtils.getSubject().getPrincipal();
			Map<String,Object> reData = new HashMap<String,Object>();
			if (user == null) {
				return ApiResult.success(null);
			}
			reData.put("userType", user.getType());
			reData.put("username", user.getName());

			SysOrg sysOrg = sysOrgService.getSysOrgByOrgId(user.getOrgId());
			if (sysOrg == null) {
				return ApiResult.success(reData);
			}
			reData.put("orgName", sysOrg.getName());
			reData.put("orgId", sysOrg.getOrgId());
			reData.put("orgType", sysOrg.getType());
			return ApiResult.success(reData);
		} catch (Exception e){
			log.error("获取用户信息出错:"+e.getMessage(),e);
			return ApiResult.fail("获取用户信息出错:"+e.getMessage());
		}
	}

	/**
	 * 判断是否修改默认密码
	 * @return
	 */
	@RequestMapping("/defPassword")
	public ApiResult defPassword() {
		HashMap<String,Object> map = new HashMap<>();
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		if(user!=null){
			//String password = user.getPassword();
			String username = user.getUsername();
			if(user.getType()==2){//规则 userName:admin_{code} password:{code}#123
				String code =user.getUsername().substring(6);// username.substring(6);
				String password2 = PasswordHelper.encryptPassword("111111");//默认密码
			//	System.out.println(password2);
				if(password2.equals(user.getPassword())){
					map.put("code",1);
					map.put("message","为默认密码");
					return ApiResult.success(map);
				}

			}else if(user.getType()==3){//规则 密码为证件后6位
				String password3 = PasswordHelper.encryptPassword(username.substring(username.length() - 6));//默认密码
				if(password3.equals(user.getPassword())){
					map.put("code",1);
					map.put("message","为默认密码");
					return ApiResult.success(map);
				}
			}
			map.put("code",0);
			map.put("message","密码已修改");
			return ApiResult.success(map);
		}else {
			return ApiResult.fail(401,"未登录，请登录后操作");
		}

	}
}
