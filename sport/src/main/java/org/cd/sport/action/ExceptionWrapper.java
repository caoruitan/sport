package org.cd.sport.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常拦截处理
 *
 * @author : liuyk
 */
public class ExceptionWrapper {

    private static Logger logger = Logger.getLogger(ExceptionWrapper.class);

    @ExceptionHandler
    public void exp(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws IOException {
        logger.error("error ---> ", ex);
        ex.printStackTrace();
        // 根据不同错误转向不同页面
       
    }
}
