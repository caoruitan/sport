package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.utils.VerifCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 验证码生成控制层
 * 
 * @author liuyk
 *
 */
@Controller
public class VerifCodeAction {

	@RequestMapping(value = "/verifCode", method = RequestMethod.GET)
	public void verifCode(HttpServletRequest request, HttpServletResponse response) {
		new VerifCode().write(request, response);
	}

	@RequestMapping(value = "/verifCode", method = RequestMethod.POST)
	public void checkCode(String verifCode, HttpServletRequest request, HttpServletResponse response) {
		String code = (String) request.getSession().getAttribute(VerifCode.KEY);
		PageWrite.writeTOPage(response, !StringUtils.isBlank(verifCode) && verifCode.equalsIgnoreCase(code));
	}

}
