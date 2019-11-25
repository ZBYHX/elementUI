package sys.service.impl;

import java.util.List;

import base.service.BaseService;
import sys.dao.IArticlesDao;
import sys.entity.Articles;
import sys.service.IArticlesService;
import util.PageBean;

public class ArticlesServiceImpl extends BaseService implements IArticlesService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6473323154737379944L;
	
	private IArticlesDao articlesDao;
	
	public ArticlesServiceImpl() {
		super();
	}
	
	public IArticlesDao getArticlesDao() {
		return articlesDao;
	}

	public void setArticlesDao(IArticlesDao articlesDao) {
		this.articlesDao = articlesDao;
	}

	@Override
	public Articles selectOneArticles(Articles articles) {
		return articlesDao.selectOneArticles(articles);
	}

	@Override
	public List<Articles> selectAllArticles(Articles articles, PageBean pageBean) {
		return articlesDao.selectAllArticles(articles, pageBean);
	}

	@Override
	public void addArticle(Articles articles) {
		articlesDao.addArticle(articles);
	}

	@Override
	public void updateArticles(Articles articles) {
		articlesDao.updateArticles(articles);
	}

	@Override
	public void deleteArticles(Articles articles) {
		articlesDao.deleteArticles(articles);
	}
	
	

}
