package com.tware.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 读取JSON文件数据
 */
@Component
public class ReadJsonUtils {

    private static final Logger log = LoggerFactory.getLogger(ReadJsonUtils.class);

    private final String CODE_JSON_PATH = "code.json";

    public JSONObject getJsonFileInfo(String path){
        try {
            ClassPathResource resource = new ClassPathResource(path); // 文件路径：code.json/xxxx.json
            String data =  IOUtils.toString(resource.getInputStream(),"utf-8");
            return (JSONObject) JSON.parse(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getJsonFileByKeyInfo(String path, String key){
        try {
            ClassPathResource resource = new ClassPathResource(path); // 文件路径：code.json/xxxx.json
            String data =  IOUtils.toString(resource.getInputStream(),"utf-8");
            JSONObject jsonObject = (JSONObject) JSON.parse(data);
            return (JSONObject) jsonObject.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getJsonFileByKeyInfo(String path, String key, String code){
        try {
            JSONObject jsonObject = this.getJsonFileByKeyInfo(path, key);
            return jsonObject.get(code).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCodeNameByKeyAndCode(String key, String code){
        try {
            JSONObject jsonObject = this.getJsonFileByKeyInfo(CODE_JSON_PATH, key);
            return jsonObject.get(code).toString();
        } catch (Exception e) {
            log.warn("未找到存在的值：key："+ key + ",code：" + code +"====" + e.getMessage());
        }
        return null;
    }

    public JSONObject getTypeByKey(String key){
        try {
            return this.getJsonFileByKeyInfo(CODE_JSON_PATH, key);
        } catch (Exception e) {
            log.warn("未找到存在的值：key："+ key +"====" + e.getMessage());
        }
        return null;
    }
}
