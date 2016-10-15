package org.cd.sport.action;

import org.cd.sport.domain.UserDomain;
import org.cd.sport.utils.AuthenticationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 数据字典控制层
 * 
 * @author liuyk
 *
 */
@Controller
@RequestMapping("/dic")
public class DicAction {

	@RequestMapping("/list")
	public String gotoIndex() {
		UserDomain user = AuthenticationUtils.getUser();
		return "dic/list";
	}
	
}
