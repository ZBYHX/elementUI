package test1;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sys.entity.User;
import sys.service.IUserService;
import util.PageBean;

public class UserServiceTest extends BaseTestCase {
	
	private IUserService userService = null;
	private User user = null;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("测试前");
		userService = (IUserService) this.getBean("userService");
		user = new User();
		pageBean = new PageBean();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("测试后");
	}

	@Test
	public void testAddUser() {
		user.setUsername("李白");
		user.setPassword("aa123");
		userService.addUser(user);
		System.out.println("添加成功！");
	}

	@Test
	public void testDelUser() {
		user.setUsername("李白");
		userService.delUser(user);
		System.out.println("删除成功！");
	}

	@Test
	public void testSelectAllUser() {
		user.setUsername("z");
		List<User> users = userService.selectAllUser(user, pageBean);
		System.out.println("集合的长度："+users.size());
		for(User u : users) {
			System.out.println(u);
		}
	}

	@Test
	public void testSelectOneUser() {
		user.setUsername("李白");
		user.setPassword("aa123");
		User u = userService.selectOneUser(user);
		System.out.println("获取的用户信息为："+u);
	}
	
	@Test
	public void testUpdateUser() {
		user.setUsername("李白");
		user.setPassword("123456");
		userService.updateUser(user);
		System.out.println("修改成功！");
	}

}
