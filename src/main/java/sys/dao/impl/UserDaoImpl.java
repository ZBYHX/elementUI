package sys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import base.dao.BaseDao;
import sys.dao.IUserDao;
import sys.entity.User;
import util.PageBean;
import util.StringUtils;

public class UserDaoImpl extends BaseDao implements IUserDao{
	private static final long serialVersionUID = 6194218899432343980L;

	public UserDaoImpl() {
		super();
	}
	
	@Override
	public void addUser(User user) {
		this.getHibernateTemplate().save(user);
	}
	
	@Override
	public void updateUser(User user) {
		User u = this.getHibernateTemplate().get(User.class, user.getUsername());
		u.setPassword(user.getPassword());
	}

	@Override
	public void delUser(User user) {
		User u = this.getHibernateTemplate().get(User.class, user.getUsername());
		if(null != u) {
			this.getHibernateTemplate().delete(u);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> selectAllUser(User user, PageBean pageBean) {
		String hql = "from User u where 1=1";
		Map<String, Object> args = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(user.getUsername())) {
			hql += " and username like :uname";
			args.put("uname", "%"+user.getUsername()+"%");
		}
		return this.executeQuery(hql, args, pageBean);
	}

	@Override
	public User selectOneUser(User user) {
		String hql = "from User u where 1=1";
		Map<String, Object> args = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(user.getUsername())) {
			hql += " and username= :uname";
			args.put("uname", user.getUsername());
		}
		if(StringUtils.isNotBlank(user.getPassword())) {
			hql += " and password= :pwd";
			args.put("pwd", user.getPassword());
		}
		
		return (User) this.queryOne(hql, args);
	}
	
	
	
}
