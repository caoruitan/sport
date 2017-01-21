package org.cd.sport.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SportListener implements ServletContextListener {

	private static String basePath;

	public static String getBasePath() {
		return basePath;
	}

	public static void setBasePath(String basePath) {
		SportListener.basePath = basePath;
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		basePath = sce.getServletContext().getRealPath("/");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
