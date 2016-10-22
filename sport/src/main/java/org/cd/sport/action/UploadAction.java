package org.cd.sport.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.utils.UUIDUtil;
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
@RequestMapping("kjsadmin")
public class UploadAction {

	public static final String DIR = "upload";

	@RequestMapping(value = "/upload.action")
	public void addUser(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/" + DIR);
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
}
