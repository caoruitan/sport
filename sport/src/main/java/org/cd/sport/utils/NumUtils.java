package org.cd.sport.utils;

import java.text.NumberFormat;

/**
 * 数字工具类
 * 
 * @author liuyk
 *
 */
public class NumUtils {
	/**
	 * 格式化数字为指定长度的字符串
	 * 
	 * @param num
	 * @param len
	 * @return
	 */
	public static String format(int num, int len) {
		// 得到一个NumberFormat的实例
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置最大整数位数
		nf.setMaximumIntegerDigits(len);
		// 设置最小整数位数
		nf.setMinimumIntegerDigits(len);
		return nf.format(num);
	}
}
