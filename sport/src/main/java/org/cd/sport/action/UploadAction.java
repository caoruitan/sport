package org.cd.sport.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.cd.sport.domain.Subject;
import org.cd.sport.service.SubjectService;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.utils.UUIDUtil;
import org.cd.sport.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

/**
 * 
 * @author 文件上传控制层
 *
 */
@Controller
public class UploadAction {

	public static final String UPLOAD_DIR = "upload";

	public static final String DOC_DIR = "doc";

	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "/news/kjsadmin/upload.action")
	public void upload(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/" + UPLOAD_DIR);
		// 原来文件名称
		String originalFilename = file.getOriginalFilename();
		String suffix = "";
		if (originalFilename.indexOf(".") != -1) {
			suffix = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
		}
		suffix = suffix.toUpperCase();
		String guid = UUIDUtil.getGuid();
		String newFileName = guid + suffix;
		JsonObject json = new JsonObject();
		json.addProperty("success", true);
		json.addProperty("id", guid);
		json.addProperty("name", originalFilename);
		json.addProperty("path", newFileName);
		json.addProperty("size", file.getSize());
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, newFileName));
			PageWrite.writeTOPage(response, json);
		} catch (IOException e) {
			json.addProperty("success", false);
			json.addProperty("msg", "文件上传失败!");
			PageWrite.writeTOPage(response, json);
		}
	}

	@RequestMapping(value = "/news/kjsadmin/download.action")
	public void download(String dataId, String dataName, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/" + UPLOAD_DIR);
		realPath = realPath + "/" + dataId;
		this.downloadFile(realPath, dataName, request, response);
	}

	@RequestMapping(value = "/sbs/download.action")
	public void downloadSbs(String subjectId, HttpServletRequest request, HttpServletResponse response) {
		// TODO 权限控制
		this.downloadSbsById(subjectId, request, response);
	}

	@RequestMapping(value = "/rws/download.action")
	public void downloadRws(String subjectId, HttpServletRequest request, HttpServletResponse response) {
		// TODO 权限控制
		this.downloadRwsById(subjectId, request, response);
	}

	public void downloadSbsById(String subjectId, HttpServletRequest request, HttpServletResponse response) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		if (subject == null) {
			throw new EntityNotFoundException("申报书不存在");
		}
		String realPath = request.getSession().getServletContext().getRealPath("/" + DOC_DIR);
		realPath = realPath + "/sbs_" + subjectId + subject.getCreator() + ".doc";
		this.downloadFile(realPath, "申报书_" + subject.getName() + ".doc", request, response);
	}

	public void downloadRwsById(String subjectId, HttpServletRequest request, HttpServletResponse response) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		if (subject == null) {
			throw new EntityNotFoundException("申报书不存在");
		}
		String realPath = request.getSession().getServletContext().getRealPath("/" + DOC_DIR);
		realPath = realPath + "/rws_" + subjectId + subject.getCreator() + ".doc";
		this.downloadFile(realPath, "任务书_" + subject.getName() + ".doc", request, response);
	}

	public void downloadFile(String filePath, String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.setContentType("application/octet-stream; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(new File(filePath)));
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
