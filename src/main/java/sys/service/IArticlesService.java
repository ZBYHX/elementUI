package sys.service;

import java.util.List;

import sys.entity.Articles;
import util.PageBean;

public interface IArticlesService {
	
	//查询单个用户信息
	public Articles selectOneArticles(Articles articles);
	
	//查询所有文章信息
	public List<Articles> selectAllArticles(Articles articles, PageBean pageBean);
	
	//新增文章信息
	public void addArticle(Articles articles);
	
	//修改文章信息
	public void updateArticles(Articles articles);
	
	//删除文章信息
	public void deleteArticles(Articles articles);
	
	
}
