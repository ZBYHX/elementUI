package sys.service.impl;

import java.util.List;
import sys.entity.User;
import base.service.BaseService;
import sys.dao.IUserDao;
import sys.service.IUserService;
import util.PageBean;

public class UserServiceImpl extends BaseService implements IUserService{
	private static final long serialVersionUID = 3226151115598321324L;
	
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);	
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public void delUser(User user) {
		userDao.delUser(user);
	}

	@Override
	public List<User> selectAllUser(User user, PageBean pageBean) {
		return userDao.selectAllUser(user, pageBean);
	}

	@Override
	public User selectOneUser(User user) {
		return userDao.selectOneUser(user);
	}

	

}
