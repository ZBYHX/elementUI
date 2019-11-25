package sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ModelDriven;

import base.action.BaseAction;
import sys.entity.Articles;
import sys.service.IArticlesService;
import util.PageBean;

public class ArticlesAction extends BaseAction implements ModelDriven<Articles> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1655387319198881219L;
	
	private Articles articles = new Articles();
	private IArticlesService articlesService;
	
	@Override
	public Articles getModel() {
		return articles;
	}
	
	public ArticlesAction() {
		super();
	}

	public IArticlesService getArticlesService() {
		return articlesService;
	}

	public void setArticlesService(IArticlesService articlesService) {
		this.articlesService = articlesService;
	}
	
	//查询所有的文章信息（包括条件查询）
	public String selectAllArticles(){
		PageBean pageBean = this.createPageBean();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Articles> list = this.articlesService.selectAllArticles(articles, pageBean);
		map.put("result", list);//获取的文章集合
		map.put("current_page", pageBean.getCurrent_page());//当前页码数
		map.put("page_size", pageBean.getPage_size());//每页显示的最大页数
		map.put("total_count", pageBean.getTotal_count());//总记录数
		this.writeJson(map);
		
		return null;
	}
	
	
	//查询单个用户信息
	public String selectOneArticles() {
		Map<String, Object> map = new HashMap<String, Object>();
		Articles article = this.articlesService.selectOneArticles(articles);
		map.put("oneResults", article);
		map.put("message", "查询单个用户信息成功！");
		map.put("code", 0);
		this.writeJson(map);
		
		return null;
	}
	
	//增加用户信息
	public String addArticle() {
		Map<String, Object> map = new HashMap<String, Object>();
		this.articlesService.addArticle(articles);
		map.put("tips", "文章增加成功！");
		map.put("code", 0);
		this.writeJson(map);
		
		return null;
	}
	
	//修改用户信息
	public String updateArticles() {
		Map<String, Object> map = new HashMap<String, Object>();
		this.articlesService.updateArticles(articles);
		map.put("message", "文章修改成功！");
		map.put("code", 0);
		this.writeJson(map);
		
		return null;
	}
	
	//增加和修改的合用方法
	public String mergeArticles() {
		Map<String, Object> map = new HashMap<String, Object>();
		if(null == articles.getId()) {
			this.articlesService.addArticle(articles);
			map.put("message", "文章增加成功！");
		}else {
			this.articlesService.updateArticles(articles);
			map.put("message", "文章修改成功！");
		}
		map.put("code", 0);
		this.writeJson(map);
		
		return null;
	}
	
	//删除文章信息
	public String deleteArticles() {
		Map<String, Object> map = new HashMap<String, Object>();
		this.articlesService.deleteArticles(articles);
		map.put("message", "文章删除成功！");
		map.put("code", 0);
		this.writeJson(map);
		
		return null;
	}

	

}
