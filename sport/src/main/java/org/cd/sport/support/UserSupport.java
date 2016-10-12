package org.cd.sport.support;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.utils.Md5Util;
import org.cd.sport.view.UserView;

/**
 * 
 * 用户相关支持类
 * 
 * @author liuyk
 *
 */
public class UserSupport extends SportSupport {

	/**
	 * 验证用户信息合法性
	 */
	public void validate(UserView user) {

	}

	public UserDomain process(UserView user) {
		this.validate(user);
		UserDomain userDomain = this.result(UserDomain.class, user);
		// 特殊处理密码
		String password = Md5Util.digestMD5(user.getPassword());
		userDomain.setPassword(password);
		return userDomain;
	}

}
