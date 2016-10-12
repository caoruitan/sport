package org.cd.sport.support;

import org.cd.sport.domain.DicType;
import org.cd.sport.view.DicTypeView;

/**
 * 
 * 用户相关支持类
 * 
 * @author liuyk
 *
 */
public class DicTypeSupport extends SportSupport {

	/**
	 * 验证用户信息合法性
	 */
	public void validate(DicTypeView dic) {

	}

	public DicType process(DicTypeView dic) {
		this.validate(dic);
		return this.result(DicType.class, dic);
	}

}
