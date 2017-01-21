package org.cd.sport.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

public class SecurityFilter extends DelegatingFilterProxy {
	private String excludedUrl;
	private String[] excludedUrls;

	@Override
	protected Filter initDelegate(WebApplicationContext wac) throws ServletException {
		excludedUrl = super.getFilterConfig().getInitParameter("excludedUrl");
		if (StringUtils.isNotEmpty(excludedUrl)) {
			excludedUrls = excludedUrl.split(",");
		}
		return super.initDelegate(wac);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean isExcludedPage = false;
		String servletPath = ((HttpServletRequest) request).getServletPath();
		for (String url : excludedUrls) {
			if (servletPath.equals(url)) {
				isExcludedPage = true;
				break;
			}
		}
		if (isExcludedPage) {
			filterChain.doFilter(request, response);
		} else {
			super.doFilter(request, response, filterChain);
		}
	}
}
