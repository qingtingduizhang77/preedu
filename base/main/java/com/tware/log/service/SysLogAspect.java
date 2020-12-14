package com.tware.log.service;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gexin.fastjson.JSON;
import com.tware.common.utils.RequestReadUtils;
import com.tware.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tware.log.annotation.ViLog;
import com.tware.log.entity.LogOperation;


@Aspect
@Component
public class SysLogAspect {

	private static final Logger log = LoggerFactory.getLogger(SysLogAspect.class);

	@Autowired
	private LogOperationService logOperationService;


	//定义切点 @Pointcut
	//在注解的位置切入代码
	@Pointcut("@annotation( com.tware.log.annotation.ViLog)")
	public void logPoinCut() {
	}

	//切面 配置通知
	@Before("logPoinCut()")         //AfterReturning
	public void saveOperation(JoinPoint joinPoint) {
		Subject subject = SecurityUtils.getSubject();
		log.info("---------------接口日志记录---------------");
		//保存日志
		LogOperation operation = new LogOperation();

		//从切面织入点处通过反射机制获取织入点处的方法
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//	        System.out.println("signature="+signature);
		//获取切入点所在的方法
		Method method = signature.getMethod();
//	        System.out.println("method="+method);

		//获取操作--方法上的ViLog的值
		ViLog viLog = method.getAnnotation(ViLog.class);
		if (viLog != null) {
			//保存操作事件
			String operEvent = viLog.operEvent();
			operation.setOperEvent(operEvent);

			//保存日志类型
			long operType = viLog.operType();
			operation.setOperType(operType);

			log.info("operEvent="+operEvent+",operType="+operType);
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			operation.setReqBody(JSON.toJSONString(joinPoint.getArgs()));
			operation.setReqParams(RequestReadUtils.getParams(request));
		} catch (Exception e) {
			log.warn("获取body数据错误：" + e.getMessage());
			e.printStackTrace();
		}
		// 请求方式：POST/GET/other
		String httpMethod = request.getMethod();
		operation.setReqType(httpMethod);
		//操作的url
		String requestURI = request.getRequestURI();
		String requestURL = request.getRequestURL().toString();
		operation.setOperUrl(requestURL);
		// 客户端ip
		String ip = RequestReadUtils.getIpAddr(request);
		operation.setClientIp(ip);
		// 操作人账号、姓名
		User loginUser = (User) subject.getPrincipal();
		if(loginUser != null) {
			String account = loginUser.getUsername();
			String username = loginUser.getName();
			operation.setIdentity(account);
			operation.setUsername(username);
			operation.setOrgId(loginUser.getOrgId());
//	          System.out.println("=================account:"+account+"--"+username);
		}

		log.info("httpMethod="+httpMethod+",URL="+requestURL);


		//操作时间
		operation.setOperTime(new Date());


		//获取用户ip地址

//	        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//	        operation.setClientIp(IPUtils.getIpAddr(request));

		//调用service保存Operation实体类到数据库

		logOperationService.insertItem(operation);

	}


}
