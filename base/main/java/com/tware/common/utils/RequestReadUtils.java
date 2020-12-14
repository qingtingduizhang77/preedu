package com.tware.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

public class RequestReadUtils {

    public static String getParams(HttpServletRequest request){
        Map<String,String[]> map = request.getParameterMap();
        String params = "";
        //遍历
        for(Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            //key值
            Object strKey = element.getKey();
            //value,数组形式
            String[] value = (String[]) element.getValue();
            if ("file".equals(strKey)) {
                continue;
            }
            params += strKey + "=";
            for (int i = 0; i < value.length; i++) {
                if (value.length - i == 1 ) {
                    params += value[i] + ";";
                }else {
                    params += value[i] + ",";
                }
            }
        }
        return params;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
