package sys.dao;

import java.util.List;
import sys.entity.User;

import util.PageBean;

/**
 * 用户dao方法的接口类
 * @author Lemons
 *
 */
public interface IUserDao {
	
	public void addUser(User user);//增加新用户
	
	public void updateUser(User user);//修改用户信息
	
	public void delUser(User user);//删除用户信息
	
	public List<User> selectAllUser(User user, PageBean pageBean);//查询多个用户信息
	
	public User selectOneUser(User user);//查询单个用户信息
	
	
}
