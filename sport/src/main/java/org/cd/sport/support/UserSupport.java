package org.cd.sport.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.UserDomain;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.utils.Md5Util;
import org.cd.sport.view.UserView;
import org.cd.sport.vo.UserVo;

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
	public void validateUpdate(UserView user) {
		if (user == null) {
			throw new ParameterIsWrongException("用户对象为空");
		}

		if (StringUtils.isBlank(user.getLoginName()) || user.getLoginName().length() < 4
				|| user.getLoginName().length() > 20) {
			throw new ParameterIsWrongException("用户名为空或者格式不对");
		}
		if (StringUtils.isBlank(user.getUserName()) || user.getUserName().length() < 2
				|| user.getUserName().length() > 20) {
			throw new ParameterIsWrongException("真实姓名为空或者格式不对");
		}

		if (StringUtils.isBlank(user.getGender())) {
			throw new ParameterIsWrongException("性别不能为空");
		}

		if (StringUtils.isBlank(user.getCredType())) {
			throw new ParameterIsWrongException("证件类型不能为空");
		}
		if (StringUtils.isBlank(user.getCredNo())) {
			throw new ParameterIsWrongException("证件编号不能为空");
		}
		if (StringUtils.isBlank(user.getRole())) {
			throw new ParameterIsWrongException("用户角色不能为空");
		}
	}

	/**
	 * 验证用户信息合法性
	 */
	public void validate(UserView user) {
		validateUpdate(user);
		// 校验password
		if (StringUtils.isBlank(user.getPassword()) || user.getPassword().length() < 6
				|| user.getPassword().length() > 16) {
			throw new ParameterIsWrongException("密码不能为空或者长度不对");
		} else {// 校验字母数字组合
			String password = user.getPassword();
			String reg1 = "\\d+$"; // 不能纯数字
			String reg2 = "[a-z]+$"; // 不能纯小写字母
			String reg3 = "[A-Z]+$"; // 不能纯大写字母
			String reg4 = "((?=[\\x21-\\x7e]+)[^A-Za-z0-9])+$"; // 不能纯标点
			if (password.matches(reg1) || password.matches(reg2) || password.matches(reg3) || password.matches(reg4)) {// 校验纯数字,纯字母等
				throw new ParameterIsWrongException("密码必须是数字字母组合");
			} else {
				for (int i = 0; i < password.length(); i++) {
					char item = password.charAt(i);
					if ((int) item < 32 || (int) item > 126) {
						throw new ParameterIsWrongException("密码必须是数字字母组合");
					}
				}
			}
		}
	}

	public UserDomain process(UserView user) {
		this.validate(user);
		UserDomain userDomain = this.result(UserDomain.class, user);
		// 特殊处理密码
		String password = Md5Util.digestMD5(user.getPassword());
		userDomain.setPassword(password);
		// 性别
		userDomain.setGender(Constants.User.parseGender(user.getGender()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parse;
		try {
			parse = simpleDateFormat.parse(user.getBirthday());
			userDomain.setBirthday(new java.sql.Date(parse.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return userDomain;
	}

	public List<UserVo> process(List<UserDomain> datas) {
		List<UserVo> vos = new ArrayList<UserVo>();
		if (datas != null && !datas.isEmpty()) {
			for (UserDomain u : datas) {
				UserVo vo = this.result(UserVo.class, u);
				// TODO 证书类型查询
				UserVo userDomain = AuthenticationUtils.getUser();
				if (userDomain != null) {
					boolean hasRole = Constants.Role.hasOper(userDomain.getRole());
					// 如果用户是管理员、不能修改信息
					boolean admin = Constants.Role.isAdmin(u.getRole());
					vo.setHasOpr(hasRole && !admin);
				}
				vos.add(vo);
			}
		}
		return vos;
	}

}
