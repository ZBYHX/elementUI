package sys.entity;

import java.io.Serializable;
/**
 * 用户实体类
 * @author Lemons
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = 7758585704147892569L;
	
	private String username;//用户名称
	private String password;//用户密码
	
//	private String jwt;//JWT
//	private String validCode;
	
	public User() {
		super();
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public String getJwt() {
//		return jwt;
//	}
//	public void setJwt(String jwt) {
//		this.jwt = jwt;
//	}
//	public String getValidCode() {
//		return validCode;
//	}
//	public void setValidCode(String validCode) {
//		this.validCode = validCode;
//	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
