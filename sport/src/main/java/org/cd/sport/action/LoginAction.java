package org.cd.sport.action;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.exception.SportException;
import org.cd.sport.utils.PageWrite;
import org.cd.sport.utils.RSAGenerator;
import org.cd.sport.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;

@Controller
public class LoginAction {

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping("login")
	public String Login(HttpServletRequest request) {
		String return_url = request.getParameter("return_url");
		// 初始化公钥
		RSAGenerator generator = new RSAGenerator();
		String pubKey = generator.generateBase64PublicKey();
		request.setAttribute("pubKey", pubKey);
		request.setAttribute("return_url", return_url);
		String guid = UUIDUtil.getGuid();
		request.setAttribute("uuid", guid);
		// 缓存 rsa对象
		request.getSession().setAttribute(Constants.User.RSA_KEY, generator);
		request.getSession().setAttribute(Constants.User.UUID_KEY, guid);
		return "login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("doLogin")
	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String uuid = request.getParameter("uuid");
		String return_url = request.getParameter("return_url");
		JsonObject json = new JsonObject();
		HttpSession session = request.getSession();
		// 解密
		RSAGenerator generator = (RSAGenerator) session.getAttribute(Constants.User.RSA_KEY);
		if (generator == null || StringUtils.isBlank(uuid) || !uuid.equals(session.getAttribute(Constants.User.UUID_KEY))) {
			json.addProperty("success", false);
			json.addProperty("msg", "非法操作");
		} else {
			try {
				password = generator.decryptBase64(password);
				UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginName,password);
				Authentication authentication = authenticationManager.authenticate(authRequest); 
				SecurityContextHolder.getContext().setAuthentication(authentication);
				session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); 
				Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
				// 用户角色 目前只有一个
				Iterator<SimpleGrantedAuthority> iterator = authorities.iterator();
				while (iterator.hasNext()) {
					SimpleGrantedAuthority next = iterator.next();
					String authority = next.getAuthority();
					if (StringUtils.isBlank(return_url)) {
						return_url = Constants.User.urlMapping.get(authority);
					}
					break;
				}
				// 移除
				session.removeAttribute(Constants.User.RSA_KEY);
				session.removeAttribute(Constants.User.UUID_KEY);
				json.addProperty("success", true);
				json.addProperty("url", return_url);
			} catch (AuthenticationException ex) {
				// 用户名不存在:UsernameNotFoundException;
				// 密码错误:BadCredentialException;
				// 帐户被锁:LockedException;
				// 帐户未启动:DisabledException;
				// 密码过期:CredentialExpiredException;等等!
				json.addProperty("success", false);
				json.addProperty("msg", "用户名或密码错误");
			} catch (SportException e) {
				json.addProperty("success", false);
				json.addProperty("msg", "非法操作");
			}
		}
		PageWrite.writeTOPage(response, json);
	}
}
