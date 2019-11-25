package test1;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sys.entity.Articles;
import sys.service.IArticlesService;
import util.PageBean;

public class ArticlesServiceTest extends BaseTestCase {
	
	private Articles articles = null;
	private IArticlesService articlesService = null;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("测试前");
		articles = new Articles();
		articlesService = (IArticlesService) this.getBean("articlesService");
		pageBean = new PageBean();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("测试后");
	}

	//查询所有文章信息
	@Test
	public void testSelectAllArticles() {
		articles.setTitle("M");
		List<Articles> list = articlesService.selectAllArticles(articles, pageBean);
		System.out.println("集合的长度：" + list.size());
		for(Articles a : list) {
			System.out.println(a);
		}
	}
	
	//查询单个用户信息
	@Test
	public void selectOneArticles() {
		articles.setId(1);
		Articles article = articlesService.selectOneArticles(articles);
		System.out.println("查询得到的单个用户信心为：" + article);
	}
	
	//新增文章信息
	@Test
	public void addArticle() {
		articles.setTitle("保卫列林格勒");
		articles.setBody("此处省略一万个字！");
		articlesService.addArticle(articles);
		System.out.println("增加成功！");
	}
	
	//修改文章信息
	@Test
	public void updateArticles() {
		articles.setId(27);
		articles.setTitle("史前一万年");
		articles.setBody("此处省略十万个字！");
		articlesService.updateArticles(articles);
		System.out.println("修改成功！");
	}
	
	//删除文章信息
	@Test
	public void deleteArticles() {
		articles.setId(27);
		articlesService.deleteArticles(articles);
		System.out.println("删除成功！");
	}
	
	

}
