package com.tware.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tware.config.entity.SysConfig;
import com.tware.config.service.SysConfigService;
import com.tware.config.springboot.InitListenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class TypeNameUtil {

    private static final Logger log = LoggerFactory.getLogger(TypeNameUtil.class);

    /**
     * 获取数据字典含义名称
     * @param key
     * @param code
     * @return
     */
    public static String getTypeName(String key, String code){
//        ReadJsonUtils utils = InitListenter.getBean("readJsonUtils", ReadJsonUtils.class);
        SysConfigService service = InitListenter.getBean("sysConfigService", SysConfigService.class);
        SysConfig sysConfig = service.getConfig("dataDic");
        return getCodeNameByKeyAndCode(sysConfig.getConfigValue(), key, code);
    }

    /**
     * 获取数据字典中的某个Key
     * @param key
     * @return
     */
    public static String getTypeKeyByValue(String key, String value){
//        ReadJsonUtils utils = InitListenter.getBean("readJsonUtils", ReadJsonUtils.class);
        SysConfigService service = InitListenter.getBean("sysConfigService", SysConfigService.class);
        SysConfig sysConfig = service.getConfig("dataDic");
        return getKey(getDataDicByKey(sysConfig.getConfigValue(), key), value);
    }


    /**
     * 通过JSONObject返回value对应的key
     * @param jsonObject
     * @param value
     * @return
     */
    public static String getKey(JSONObject jsonObject, String value) {
        String keyValue = "";
        Iterator<String> keys = jsonObject.keySet().iterator();// jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (jsonObject.get(key) instanceof String) {
                if (((jsonObject.get(key)).equals(value))) {
                    keyValue = key;
                    break;
                }
            } else if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject innerObject = (JSONObject) jsonObject.get(key);
                keyValue = getKey(innerObject, value);
                if (!keyValue.equals("")) {
                    break;
                }
            } else if (jsonObject.get(key) instanceof JSONArray) {
                JSONArray innerObject = (JSONArray) jsonObject.get(key);
                keyValue = getKey_(innerObject, key, value);
                if (!keyValue.equals("")) {
                    break;
                }
            } else if (jsonObject.get(key) instanceof Integer) {
                if ((jsonObject.get(key).toString()).equals(value)) {
                    keyValue = key;
                    break;
                }
            }
        }
        return keyValue;
    }

    public static String getKey_(JSONArray json1, String key, String value) {
        String keyValue = "";
        if (json1 != null) {
            Iterator<Object> i1 = json1.iterator();
            while (i1.hasNext()) {
                Object ele = i1.next();
                if (ele instanceof JSONObject) {
                    JSONObject innerObject = (JSONObject) ele;
                    keyValue = getKey(innerObject, value);
                    if (!keyValue.equals("")) {
                        break;
                    }
                } else if (ele instanceof JSONArray) {
                    JSONArray innerObject = (JSONArray) ele;
                    keyValue = getKey_(innerObject, key, value);
                    if (!keyValue.equals("")) {
                        break;
                    }
                } else if (ele instanceof String) {
                    String innerObject = (String) ele;
                    if (innerObject.equals(value)) {
                        keyValue = key;
                        break;
                    }
                } else if (ele instanceof Integer) {
                    Integer innerObject = (Integer) ele;
                    if (innerObject.toString().equals(value)) {
                        keyValue = key;
                        break;
                    }
                }
            }
        }
        return keyValue;
    }


    public static JSONObject getDataDicByKey(String data, String key){
        JSONObject jsonObject = (JSONObject) JSON.parse(data.substring(1, data.length()-1).replace("\\", ""));
        return (JSONObject) jsonObject.get(key);
    }

    public static String getCodeNameByKeyAndCode(String data, String key, String code){
        try {
            JSONObject jsonObject = getDataDicByKey(data, key);
            return jsonObject.get(code).toString();
        } catch (Exception e) {
            log.warn("未找到存在的值：key："+ key + ",code：" + code +"====" + e.getMessage());
        }
        return null;
    }
}
