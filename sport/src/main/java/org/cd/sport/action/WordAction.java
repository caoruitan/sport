package org.cd.sport.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("word")
public class WordAction {

	@RequestMapping("/index")
	public String wordIndex() {
		return "word/Word";
	}

	@RequestMapping("/kjsadmin/open")
	public String kjsadminOpenWord(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("readonly", true);
		return "word/openWord";
	}

	@RequestMapping("/kjsExpert/open")
	public String kjsExpertOpenWord(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("readonly", true);
		return "word/openWord";
	}

	@RequestMapping("/kjsLeader/open")
	public String kjsLeaderOpenWord(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("readonly", true);
		return "word/openWord";
	}

	@RequestMapping("/orgAdmin/open")
	public String orgAdminOpenWord(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("readonly", true);
		return "word/openWord";
	}

	@RequestMapping("/orgOper/open")
	public String orgOperOpenWord(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("readonly", request.getParameter("readonly"));
		return "word/openWord";
	}

	@RequestMapping("/sbAdmin/open")
	public String sbAdminOperOpenWord(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("readonly", true);
		return "word/openWord";
	}

	@RequestMapping("/open")
	public String sboperOpenWord(HttpServletRequest request, HttpServletResponse response) {
		String readonly = request.getParameter("readonly");
		request.setAttribute("readonly", StringUtils.isBlank(readonly) ? true : Boolean.parseBoolean(readonly));
		return "word/openWord";
	}

	@RequestMapping("/SaveFile")
	public String saveFilePage() {
		return "word/SaveFile";
	}
}
