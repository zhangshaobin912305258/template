package com.zhang.template.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

@Aspect
@Slf4j
@Component
public class AopConfig {

    @Pointcut("execution(* com.zhang.template.controller..*(..)))")
    private void logPointCut() {
    }

    @Around("logPointCut()")
    public Object logPrint(ProceedingJoinPoint point) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        Object[] args = point.getArgs();
        String params = "";
        // result的值就是被拦截方法的返回值
        long startTime = System.currentTimeMillis();
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();
        try {
            //获取请求参数集合并进行遍历拼接
            if (args.length > 0) {
                if ("POST".equals(method)) {
                    Object[] arguments = new Object[args.length];
                    for (int i = 0; i < args.length; i++) {
                        if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                            //ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                            //ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
                            continue;
                        }
                        arguments[i] = args[i];
                    }
                    if (arguments != null) {
                        try {
                            params = JSONUtil.toJsonStr(arguments);
                        } catch (Exception e) {
                            params = arguments.toString();
                        }
                    }
                } else if ("GET".equals(method)) {
                    params = queryString;
                }
                if (StringUtils.isNotEmpty(params)) {
                    params = URLDecoder.decode(params, "UTF-8");
                }
            }
            log.info("requestMethod:{},请求地址:{},请求参数:{},响应:{},请求耗时:{}ms.", method, uri, params, JSONUtil.toJsonStr(result), (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("requestMethod:{},请求地址:{},请求参数:{},响应:{},请求耗时:{}ms.", method, uri, params, JSONUtil.toJsonStr(result), (endTime - startTime));
        }
        return result;
    }

}
