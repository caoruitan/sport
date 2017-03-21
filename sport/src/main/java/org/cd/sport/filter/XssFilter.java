package org.cd.sport.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.utils.IPUtils;

/**
 * xss过滤器
 * 
 * @author : liuyk
 */
public class XssFilter implements Filter {

	private static List<String> hosts = new ArrayList<String>();

	static {
		hosts.add("125.35.8.51");
		hosts.add("172.16.5.101");
		//hosts.add("localhost");
		hosts.add(IPUtils.getIp());
	}

	FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String myhosts = ((HttpServletRequest) request).getHeader("host");
		if (StringUtils.isNotBlank(myhosts)) {
			if (!hosts.contains(myhosts.split(":")[0])) {
				((HttpServletResponse) response).sendRedirect("/login");
				return;
			}
		}
		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);
	}
}