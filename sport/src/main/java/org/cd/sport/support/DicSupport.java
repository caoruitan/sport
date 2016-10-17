package org.cd.sport.support;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.Dic;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.view.DicView;

/**
 * 
 * 用户相关支持类
 * 
 * @author liuyk
 *
 */
public class DicSupport extends SportSupport {

	/**
	 * 验证用户信息合法性
	 */
	public void validate(DicView dic) {
		if (dic == null) {
			throw new ParameterIsWrongException("数据字典对象为空");
		}

		if (StringUtils.isBlank(dic.getpCode())) {
			throw new ParameterIsWrongException("数据字典类型为空");
		}

		if (StringUtils.isBlank(dic.getName()) || dic.getName().length() > 50) {
			throw new ParameterIsWrongException("数据字典名称为空或者长度超出50");
		}
		if (!StringUtils.isBlank(dic.getValue()) && dic.getValue().length() > 20) {
			throw new ParameterIsWrongException("数据字典值长度超出20");
		}

		if (!StringUtils.isBlank(dic.getDescription()) && dic.getDescription().length() > 200) {
			throw new ParameterIsWrongException("数据字典描述长度超出200");
		}
	}

	public void validateUpdate(DicView dic) {
		this.validate(dic);
		if (StringUtils.isBlank(dic.getId())) {
			throw new ParameterIsWrongException("数据字典id为空");
		}

	}

	public Dic process(DicView dic) {
		this.validate(dic);
		return this.result(Dic.class, dic);
	}

}
