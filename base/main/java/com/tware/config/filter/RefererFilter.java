package com.tware.config.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class RefererFilter implements Filter {

    @Value("${whiteIp}")
    private String whiteIp;

    @Value("${whitePath}")
    private String whitePath;

    @Override
    public void init(FilterConfig config) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws ServletException, IOException {
        List<String> whiteIpArr = Arrays.asList(whiteIp.split("#%#"));// 白名单
        List<String> whitePathArr = Arrays.asList(whitePath.split("#%#"));// 白名单
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 链接来源地址
        String referer = request.getHeader("referer");
        String url = request.getRequestURL().toString();
        for (String path : whitePathArr) {
            if (url.contains(path)) {
                chain.doFilter(request, response);
                return;
            }
        }
        boolean flag = false;// 是否跳过拦截  false:拦截；true：不拦截
        for (String ip : whiteIpArr) {
            if (referer != null && referer.contains(ip)) {
                flag = true;
                break;
            }
        }
        if (referer == null || !flag) {
            /**
             * 如果 链接地址来自其他网站，则返回错误页面
             */
            System.out.println("被拦截的url:" + url);
            request.getRequestDispatcher("/api/public/loginFailure").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
