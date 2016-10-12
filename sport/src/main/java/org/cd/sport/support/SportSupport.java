package org.cd.sport.support;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.cd.sport.constant.Constants;
import org.cd.sport.exception.ParameterIsWrongException;
import org.springframework.beans.BeanUtils;

/**
 * 公共支持类
 * 
 * @author liuyk
 *
 */
public class SportSupport {

	private static Logger logger = Logger.getLogger(SportSupport.class);

	/**
	 * 拷贝集合对象
	 */
	public <T, R> List<R> result(Class<R> clazz, List<T> src) {
		if (src == null) {
			return null;
		}
		List<R> rs = new ArrayList<R>();
		try {
			for (T t : src) {
				R r = clazz.newInstance();
				BeanUtils.copyProperties(t, r);
				rs.add(r);
			}
		} catch (InstantiationException e) {
			logger.error("拷贝对象失败", e);
		} catch (IllegalAccessException e) {
			logger.error("拷贝对象失败", e);
		}
		return rs;
	}

	/**
	 * 拷贝单个对象
	 */
	public <T, R> R result(Class<R> clazz, T src) {
		if (clazz == null) {
			return null;
		}
		if (src == null) {
			return null;
		}
		try {
			R r = clazz.newInstance();
			BeanUtils.copyProperties(src, r);
			return r;
		} catch (InstantiationException e) {
			logger.error("拷贝对象失败", e);
		} catch (IllegalAccessException e) {
			logger.error("拷贝对象失败", e);
		}
		return null;
	}

	public void valid(String parameter) {
		if (StringUtils.isBlank(parameter)) {
			throw new ParameterIsWrongException();
		}
	}

	public <T> void valid(T parameter) {
		if (parameter == null) {
			throw new ParameterIsWrongException();
		}
	}

	public static int processLimit(String limit) {
		if (StringUtils.isBlank(limit)) {
			return 1;
		}
		try {
			int parseInt = Integer.parseInt(limit);
			return parseInt < 1 ? 1 : (parseInt > Integer.MAX_VALUE ? 1 : parseInt);
		} catch (Exception e) {
			return 1;
		}
	}

	public static int processPageSize(String limit) {
		if (StringUtils.isBlank(limit)) {
			return Constants.Common.PAGE_SIZE;
		}
		try {
			int parseInt = Integer.parseInt(limit);
			return parseInt < 0 ? Constants.Common.PAGE_SIZE
					: (parseInt > Integer.MAX_VALUE ? Constants.Common.PAGE_SIZE : parseInt);
		} catch (Exception e) {
			return Constants.Common.PAGE_SIZE;
		}
	}

}
