package org.cd.sport.support;

import org.cd.sport.domain.Dic;
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

	}

	public Dic process(DicView dic) {
		this.validate(dic);
		return this.result(Dic.class, dic);
	}

}
