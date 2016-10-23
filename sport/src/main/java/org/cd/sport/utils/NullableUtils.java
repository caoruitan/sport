package org.cd.sport.utils;

import java.util.Iterator;
import java.util.List;

/**
 * 清除集合中的空元素
 *
 * @author : liuyk
 */
public class NullableUtils {

	public static <T extends Nullable> void clean(List<T> list) {
		if (list != null && !list.isEmpty()) {
			for (Iterator<T> iterator = list.iterator(); iterator.hasNext();) {
				T nullable = (T) iterator.next();
				if (nullable.isNull()) {
					iterator.remove();
				}
			}
		}
	}
}
