package test1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import util.PageBean;


public class BaseTestCase {
	
	public static ApplicationContext applicationContext = null;
	
	protected PageBean pageBean;
	
	static {
		applicationContext = new ClassPathXmlApplicationContext("spring.xml");
	}
	
	public Object getBean(String name) {
		return applicationContext.getBean(name);
	}
	
}
