package com.tware.common.control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.tware.config.entity.SysConfig;
import com.tware.config.service.SysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tware.common.service.UserCheckCodeService;
import com.tware.common.smsConfig.MessageCode;
import com.tware.common.smsConfig.smsConfig;
import com.tware.common.utils.AliyunSmsBuilder;
import com.tware.common.utils.AliyunSmsUtil;
import com.tware.common.utils.InputCheckUtil;
import com.tware.common.utils.RedisUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import swallow.framework.web.ApiResult;

@Api(tags  = "验证码接口")
@RestController
@RequestMapping("/api/public")
public class UserCheckCodeControl {

	private static final Logger logger = LoggerFactory.getLogger(UserCheckCodeControl.class);

	private DefaultKaptcha kaptcha;

	@Autowired
	private UserCheckCodeService service;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private AliyunSmsUtil aliyunSmsUtil;
	@Autowired
	private SysConfigService sysConfigService;

	@PostConstruct
	public void init() {
		kaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		// 图片边框
		properties.setProperty("kaptcha.border", "no");
		// 边框颜色
		properties.setProperty("kaptcha.border.color", "105,179,90");
		// 字体颜色
		properties.setProperty("kaptcha.textproducer.font.color", "blue");
		// 图片宽
		properties.setProperty("kaptcha.image.width", "110");
		// 图片高
		properties.setProperty("kaptcha.image.height", "40");
		// 字体大小
		properties.setProperty("kaptcha.textproducer.font.size", "30");
		// session key
		properties.setProperty("kaptcha.session.key", "code");
		// 验证码长度
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		// 字体
		properties.setProperty("kaptcha.textproducer.font.names", "Arial, Courier");
		Config config = new Config(properties);

		kaptcha.setConfig(config);
	}

	/**
	 * 取得当前验证码的图片
	 * 
	 * @param width
	 * @param height
	 * @param key
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value="验证码图片",httpMethod="GET")
	@RequestMapping(value = "checkimg/{key}/{width}/{height}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] getImageCheckCode(@ApiParam("验证码key") @PathVariable("key") String key, @ApiParam("图片的宽度") @PathVariable("width") int width,
									@ApiParam("图片的高度") @PathVariable("height") int height, HttpServletResponse response) throws IOException {

		Assert.hasText(key, "参数key不允许为空");

		kaptcha.getConfig().getProperties().setProperty("kaptcha.image.width", width + "");
		kaptcha.getConfig().getProperties().setProperty("kaptcha.image.height", height + "");
		kaptcha.getConfig().getProperties().setProperty("kaptcha.background.clear.from", "white");
		kaptcha.getConfig().getProperties().setProperty("kaptcha.background.clear.to", "white");

		if (height < 40) {
			kaptcha.getConfig().getProperties().setProperty("kaptcha.textproducer.font.size", "28");
		} else {
			kaptcha.getConfig().getProperties().setProperty("kaptcha.textproducer.font.size", "35");
		}


		var checkCode = kaptcha.createText();
		String id = service.createChechCodeKey();
		logger.info("id=" + id);
		// 如果checkCode已经过期，重新生成
		this.service.putUserCheckCode(id, checkCode);

		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		BufferedImage challenge = kaptcha.createImage(checkCode);
		ImageIO.write(challenge, "jpg", jpegOutputStream);

		SysConfig sysConfig = sysConfigService.getConfig("isCheckCode");

		// 禁用缓存
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type,x-requested-with");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		if (sysConfig != null && "0".equals(sysConfig.getConfigValue())) {// 前端是否需要验证码
			response.setHeader("id", "NONE");
		}else {
			response.setHeader("id", id);
		}
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		return jpegOutputStream.toByteArray();
	}

	


	/**
	 * 前端全局变量数据字典
	 * @return
	 */
	@RequestMapping("/data_dictionary")
	public String getDataDictionary(){
		SysConfig sysConfig = sysConfigService.getConfig("webDataDic");
		return "var SYS_DIRCT_DATA= " + sysConfig.getConfigValue();
	}

}
