package sys.action;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ModelDriven;
import base.action.BaseAction;
import base.dao.BaseDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jwt.JwtUtils;
import sys.entity.User;
import sys.service.IUserService;
import util.JsonData;
import util.PageBean;

/**
 * 用户信息的Action层
 * @author Lemons
 *
 */
public class UserAction extends BaseAction implements ModelDriven<User>{
	
	private static final long serialVersionUID = -724884018556173241L;
	
	private User user = new User();
	private IUserService userService;
	
	private Logger log = LogManager.getLogger(UserAction.class);
	
	public UserAction() {}
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	
	public IUserService getUserService() {
		return userService;
	}
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	
	//增加新用户
	public String addUser() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		this.userService.addUser(user);
		map.put("message", "保存用户信息成功！");
		this.writeJson(map);
		
		return null;
	}
	
//	//修改用户信息
//	public String updatUser(){
//		return null;
//	}
	
	//删除用户信息
	public String delUser(){
		Map<String, Object> map = new HashMap<String, Object>();
		this.userService.delUser(user);
		map.put("message", "保存用户信息成功！");
		this.writeJson(map);
		
		return null;
	}
	
	//查询多个用户信息
	public String selectAllUser(){
		PageBean pageBean = this.createPageBean();
		List<User> users = this.userService.selectAllUser(user, pageBean);
		this.writeJson(users);
		
		return null;
	}
	
	//查询单个用户信息(用户登录)
	public String selectOneUser(){
		log.info(user);
		JsonData jsonData = new JsonData();
		
		//图片验证码的校验
//		String errorMsg = null;
//		try {
//			Claims claims2 = JwtUtils.parseJwt(user.getJwt());
//			String oldValidCode = (String)claims2.get("validCode");
//			if(oldValidCode.equalsIgnoreCase(user.getValidCode())) {
//				errorMsg = "验证码错误";
//			}
//			
//		} catch (ExpiredJwtException e) {
//			errorMsg = "验证码已过期";
//		} catch (SignatureException e) {
//			errorMsg = "验证码已失效";
//		} catch (Exception e) {
//			errorMsg = "验证码错误";//其他类型的都捕获
//		}
//		if(null != errorMsg) {
//			jsonData.setCode(-1);
//			jsonData.setMessage(errorMsg);
//			this.writeJson(jsonData);
//		}
		
		
		User u = userService.selectOneUser(user);
		if(null == u || !u.getPassword().equals(user.getPassword())) {
			jsonData.setCode(-1);
			jsonData.setMessage("账号或密码错误！");
		}else {
			Map<String, Object> claims = new HashMap<String, Object>();
			claims.put("username", u.getUsername());
			String jwt = JwtUtils.createJwt(claims, JwtUtils.JWT_WEB_TTL);
			System.out.println(jwt);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With, Content-Type, Accept, jwt");
			response.setHeader(JwtUtils.JWT_HEADER_KEY, jwt);
			
			jsonData.setCode(0);
			jsonData.setMessage("登陆成功！");
//			jsonData.setResult(jwt);
			
		}
		this.writeJson(jsonData);
		
		
		
		//自定义
//		Map<String, Object> map = new HashMap<String, Object>();
//		User u = this.userService.selectOneUser(user);
//		if(null != u) {
//			map.put("code", "0");
//			map.put("message", "用户登录成功");
//			map.put("user", u);
//		}else {
//			map.put("code", "-1");
//			map.put("message", "帐号或者密码错误");
//		}
//		this.writeJson(map);
		
		
		return null;
	}
	
	//修改用户信息(只能修改用户的密码)
	public String updateUser() {
		System.out.println("用户对象："+user);
		Map<String, Object> map = new HashMap<String, Object>();
		this.userService.updateUser(user);
		map.put("message", "密码修改成功！");
		this.writeJson(map);
		
		return null;
	}
	
	
	
	
}
