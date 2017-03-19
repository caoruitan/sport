package org.cd.sport.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.cd.sport.constant.Constants;

/**
 * 
 * url合法性校验工具类
 * 
 * @author liuyk
 *
 */
public final class URLValidateUtils {
	/**
	 * 判断url是否为合法的url
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isUrl(String url) {
		if (url == null) {
			return false;
		}
		String regEx = "^(http|https)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(url);
		return matcher.matches();
	}

	/**
	 * 校验returnurl
	 * 
	 * @param url
	 * @return
	 */
	public static String validate(HttpServletRequest request) {
		String return_url = request.getParameter("return_url");
		// 判断是否为url
		boolean isUrl = isUrl(return_url);
		String basePath = getBasePath(request);
		if (isUrl) {
			// 校验是否为本站连接
			if (return_url.startsWith(basePath)) {
				return return_url;
			}
		}
		return basePath;
	}

	private static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	}

	/**
	 * 校验returnurl
	 * 
	 * @param url
	 * @return
	 */
	public static String validate(HttpServletRequest request, String role) {
		String return_url = request.getParameter("return_url");
		// 判断是否为url
		boolean isUrl = isUrl(return_url);
		String basePath = getBasePath(request);
		if (isUrl) {
			// 校验是否为本站连接
			if (return_url.startsWith(basePath) && !basePath.equals(return_url)) {
				return return_url;
			}
		}
		return Constants.User.urlMapping.get(role);
	}
}
