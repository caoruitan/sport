package org.cd.sport.security;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.utils.RSAUtils;
import org.springframework.stereotype.Component;
import com.alibaba.druid.util.DruidPasswordCallback;

/**
 * 数据库密码解密类
 * 
 * @author liuyk
 *
 */
@SuppressWarnings("serial")
@Component("dbPasswordCallback")
public class DBPasswordCallback extends DruidPasswordCallback {

	private String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIDyEluJ1dKoaRI3MutPblpyfRL7mlDPgdMlLadW4npxMtqW/2/4w7/5kKACW1Hgu5ovzyWzTjLOZqEFYlpSvGzK4FI9Sxkf3uxrVnS8Gr0a8pzPJF97ZLjNIZXIBtg6h+zylr+je+rr89+DGxt9KPSuWUS2QBwCFJkjmdCCKgFdAgMBAAECgYB0V/ZwWYFwPTvwj5WF83liaoKnqhSmbVR6KsVyg1Z9m72CtVNsuEwG3yq69OVojxk2GYUSQyJ/fPPkvXLshFI1l4dD5Kzrc/ut4dJfV3kdP11SRfApn4+qkcThB0LO+j8lkkROMfxP7Jo5mZeK/PrZxRGGq3Ms/ebKqIod1drwAQJBAOHAosiSZW+QIAvOyejrX7YiK4lD6OCxK8uMjp1CLhu5kBgUc/Q4AmhRIxF4dxdDbS1I9Fuh/arCW7dpN1heJwECQQCSOO+z9dktL5E+66tEZx+ABpYTP4TtQ57fDdaBUfr8rpqQ3j93UeRsYv/Ta6hYFjsMkvS1Cj7BFAi/IsTWW9ZdAkAveWlCMLcwLOjwUr5XQxhPnwP0y9kvV1LUbWyYWZCEuBM7XSG9XAH1mgp75A93TvCos0zf8EL+I/1RpsjCxpoBAkAQthUfGh/h/Ce7ay+9t5NwDFCmjoHgo/PKRkiXqwjmTAY6PExAlMloYcAfJ5SrzI6fF0SEVFFru6BsR8r7PK+5AkEAvu/n8VshN770Myg6sGBXYoEwTwxqV8IuFYXwAu6J6NILyj+BN8f+OjNmhNrn6tQlKiYoib96jQmPR9SxcP4Qrg==";

	public void setProperties(Properties properties) {
		super.setProperties(properties);
		String pwd = properties.getProperty("password");
		if (StringUtils.isNotBlank(pwd)) {
			try {
				String decrypt = RSAUtils.decrypt(pwd, privateKey);
				setPassword(decrypt.toCharArray());
			} catch (Exception e) {
				setPassword(pwd.toCharArray());
			}
		}
	}
}
