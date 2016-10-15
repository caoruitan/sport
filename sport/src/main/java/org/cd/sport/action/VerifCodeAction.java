package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cd.sport.utils.VerifCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 验证码生成控制层
 * 
 * @author liuyk
 *
 */
@Controller
public class VerifCodeAction {

	@RequestMapping("/verifCode")
	public void verifCode(HttpServletRequest request, HttpServletResponse response) {
		new VerifCode().write(request, response);
	}
	
}
