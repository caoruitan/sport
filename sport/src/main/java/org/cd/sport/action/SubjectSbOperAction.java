package org.cd.sport.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Subject;
import org.cd.sport.service.SubjectSbsService;
import org.cd.sport.service.SubjectService;
import org.cd.sport.support.SportSupport;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.utils.GsonUtils;
import org.cd.sport.utils.PageModel;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.vo.SubjectVo;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonObject;

@Controller
@RequestMapping("subject/sboper")
public class SubjectSbOperAction {

	@Autowired
	private SubjectService subjectService; 
	
	@Autowired
	private SubjectSbsService subjectSbsService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, HttpServletResponse response) {
		int startYear = Integer.parseInt(Constants.Subject.SUBJECT_START_YEAR);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int endYear = Integer.parseInt(sdf.format(date));
		List<String> years = new LinkedList<String>();
		for(int i = 0; endYear - i >= startYear; i ++) {
			years.add(String.valueOf(endYear - i));
		}

		request.setAttribute("endYear", String.valueOf(endYear));
		request.setAttribute("years", years);
		request.setAttribute("types", Constants.Subject.getSubjectTypes());
		request.setAttribute("stages", Constants.Subject.getSubjectStages());
		return "subject/sboper/list";
	}

	@RequestMapping("/datas.action")
	public void datas(HttpServletRequest request, HttpServletResponse response) {
		String year = request.getParameter("year");
		String type = request.getParameter("type");
		String stage = request.getParameter("stage");
		String startStr = request.getParameter("page");
		UserVo user = AuthenticationUtils.getUser();
		if(year == null || year.equals("")) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			year = sdf.format(date);
		}
		int start = SportSupport.processLimit(startStr);
		List<Subject> list = subjectService.getSubjectListByCreator(user.getLoginName(), year, type, stage, (start - 1) * Constants.Common.PAGE_SIZE, Constants.Common.PAGE_SIZE);
		long total = subjectService.getSubjectCountByCreator(user.getLoginName(), year, type, stage);
		PageModel<Subject> page = new PageModel<Subject>();
		page.setPage(start);
		page.setTotal((long) Math.ceil(new Double(String.valueOf(total))/Constants.Common.PAGE_SIZE));
		page.setRecords(total);
		page.setRows(list);
		PageWrite.writeTOPage(response, GsonUtils.toJson(page));
	}
	
	@RequestMapping(value = "createSubject", method = RequestMethod.GET)
	public String toCreateSubject(HttpServletRequest request) {
		request.setAttribute("types", Constants.Subject.getSubjectTypes());
		return "subject/sboper/create";
	}
	
	@RequestMapping(value = "createSubject", method = RequestMethod.POST)
	public String createSubject(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String organizationId = request.getParameter("organizationId");
		String security = request.getParameter("security");
		String organizationCount = request.getParameter("organizationCount");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String[] resultsList = request.getParameterValues("results");
		String results = "";
		if(resultsList != null && resultsList.length > 0) {
			for(String result : resultsList) {
				results += result + ",";
			}
			if(!results.equals("")) {
				results = results.substring(0, results.length() - 1);
			}
		}
		String integration = request.getParameter("integration");
		SubjectVo vo = new SubjectVo();
		vo.setName(name);
		vo.setType(type);
		vo.setOrganizationId(organizationId);
		vo.setSecurity(security);
		vo.setOrganizationCount(organizationCount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		vo.setBeginDate(sdf.parse(beginDate));
		vo.setEndDate(sdf.parse(endDate));
		vo.setResults(results);
		vo.setIntegration(Boolean.valueOf(integration));
		subjectService.createSubject(vo);
		return "redirect:list.htm";
	}
	
	@RequestMapping(value = "detail")
	public String detail(HttpServletRequest request) {
		String subjectId = request.getParameter("subjectId");
		Subject subject = subjectService.getSubjectById(subjectId);
		request.setAttribute("subject", subject);
		request.setAttribute("types", Constants.Subject.getSubjectTypes());
		return "subject/sboper/detail";
	}
	
	@RequestMapping(value = "sbstb")
	public String sbstb(HttpServletRequest request) {
		String subjectId = request.getParameter("subjectId");
		Subject subject = subjectService.getSubjectById(subjectId);
		request.setAttribute("subjectId", subjectId);
		request.setAttribute("subject", subject);
		return "subject/sboper/sbstb";
	}
	
	@RequestMapping(value = "saveBaseInfo")
	public void saveBaseInfo(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String fax = request.getParameter("fax");
		String years = request.getParameter("years");
		this.subjectSbsService.saveBaseInfo(subjectId, address, phone, fax, email, years);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "saveXtyj")
	public void saveXtyj(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String xtyj = request.getParameter("xtyj");
		this.subjectSbsService.saveXtyj(subjectId, xtyj);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "saveYjmb")
	public void saveYjmb(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String yjmb = request.getParameter("yjmb");
		this.subjectSbsService.saveYjmb(subjectId, yjmb);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "saveJsgj")
	public void saveJsgj(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String jsgj = request.getParameter("jsgj");
		this.subjectSbsService.saveJsgj(subjectId, jsgj);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "saveYjff")
	public void saveYjff(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String yjff = request.getParameter("yjff");
		this.subjectSbsService.saveYjff(subjectId, yjff);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "saveSyfa")
	public void saveSyfa(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String syfa = request.getParameter("syfa");
		this.subjectSbsService.saveSyfa(subjectId, syfa);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "saveJdap")
	public void saveJdap(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String jdap = request.getParameter("jdap");
		this.subjectSbsService.saveJdap(subjectId, jdap);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "saveYqjg")
	public void saveYqjg(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String yqjg = request.getParameter("yqjg");
		this.subjectSbsService.saveYqjg(subjectId, yqjg);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "saveGztj")
	public void saveGztj(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String gztj = request.getParameter("gztj");
		this.subjectSbsService.saveGztj(subjectId, gztj);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "saveTjyj")
	public void saveTjyj(HttpServletRequest request, HttpServletResponse response) {
		String subjectId = request.getParameter("subjectId");
		String tjyj = request.getParameter("tjyj");
		this.subjectSbsService.saveTjyj(subjectId, tjyj);
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		PageWrite.writeTOPage(response, json);
	}
	
	@RequestMapping(value = "testWord")
	public void testWord(HttpServletRequest request) throws IOException {
		String path = request.getSession().getServletContext().getRealPath("/");
		FileInputStream in = new FileInputStream(new File(path + "/WEB-INF/doc/aaa.doc"));
		HWPFDocument hdt = new HWPFDocument(in);
		Range range = hdt.getRange();  
		range.replaceText("${address}", "北京市海淀区宝盛南路奥北科技园");
		range.replaceText("${phone}", "15910949461");
		range.replaceText("${email}", "caort@sysware.com.cn");
		range.replaceText("${fax}", "010-82845536");
		range.replaceText("${content}", "<p>研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容研究目标和主要研究内容</p><p>本项目的技术关键与创新点</p>");
		OutputStream os = new FileOutputStream(path + "/WEB-INF/doc/write.doc");
		// 把doc输出到输出流中
		hdt.write(os);
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
