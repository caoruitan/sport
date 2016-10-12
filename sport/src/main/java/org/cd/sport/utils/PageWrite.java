package org.cd.sport.utils;

import java.io.IOException;
import javax.servlet.ServletResponse;

public class PageWrite {

    /**
     * 向页面中写入JSON数据
     * 
     * @param response 输出对象
     * @param psJsonData 输出数据
     */
    public static void writeTOPage(ServletResponse response, Object psJsonData) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/JSON");
        try {
            response.getWriter().print(psJsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
