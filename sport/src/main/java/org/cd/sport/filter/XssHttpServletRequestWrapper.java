package org.cd.sport.filter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	// XSS处理Map
	private static Map<String, String> xssMap = new LinkedHashMap<String, String>();

	public void init() {
		// 含有脚本： script
		xssMap.put("[s|S][c|C][r|R][i|C][p|P][t|T]", "");
		// 含有脚本 javascript
		xssMap.put("[\\\"\\\'][\\s]*[j|J][a|A][v|V][a|A][s|S][c|C][r|R][i|I][p|P][t|T]:(.*)[\\\"\\\']", "\"\"");
		// 含有函数： eval
		xssMap.put("[e|E][v|V][a|A][l|L]\\((.*)\\)", "");
		// 含有符号 <
		xssMap.put("<", "&lt;");
		// 含有符号 >
		xssMap.put(">", "&gt;");
		// 含有符号 (
		xssMap.put("\\(", "&#40;");
		// 含有符号 )
		xssMap.put("\\)", "&#41;");
		// 含有符号 '
		xssMap.put("'", "'");
	}

	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
		this.init();
	}

	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		Set<String> keySet = xssMap.keySet();
		for (String key : keySet) {
			String v = xssMap.get(key);
			value = value.replaceAll(key, v);
		}
		return value;
	}
}