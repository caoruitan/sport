package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestAction {
    
    @RequestMapping("index")
    public String toLogin(HttpServletRequest request) {
        return "test/index";
    }
}
