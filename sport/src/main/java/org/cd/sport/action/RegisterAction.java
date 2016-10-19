package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册控制层
 * 
 * @author liuyk
 *
 */
@Controller
public class RegisterAction {

	@RequestMapping("/register.htm")
	public String register(HttpServletRequest request) {
		return "register/one";
	}
	
}
