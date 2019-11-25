package sys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.dao.BaseDao;
import sys.dao.IArticlesDao;
import sys.entity.Articles;
import util.PageBean;
import util.StringUtils;

public class ArticlesDaoImpl extends BaseDao implements IArticlesDao{
	
	private static final long serialVersionUID = -9153764894552802244L;
	
	public ArticlesDaoImpl() {
		super();
	}
	
	//查询单个文章信息
	@Override
	public Articles selectOneArticles(Articles articles) {
		Articles article = this.getHibernateTemplate().get(Articles.class, articles.getId());
		return article;
	}
	
	//查询所有文章信息（包括条件查询）
	@SuppressWarnings("unchecked")
	@Override
	public List<Articles> selectAllArticles(Articles articles, PageBean pageBean) {
		String hql = "from Articles u where 1=1";
		Map<String, Object> args = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(articles.getTitle())) {
			hql += " and u.title like :uname";
			args.put("uname", "%"+articles.getTitle()+"%");
		}
		return  this.executeQuery(hql, args, pageBean);
	}
	
	//增加文章信息
	@Override
	public void addArticle(Articles articles) {
		this.getHibernateTemplate().save(articles);
	}
	
	//修改文章信息
	@Override
	public void updateArticles(Articles articles) {
		Articles article = this.getHibernateTemplate().get(Articles.class, articles.getId());
		if(null != article) {
			article.setTitle(articles.getTitle());
			article.setBody(articles.getBody());
		}
	}
	
	//删除文章信息
	@Override
	public void deleteArticles(Articles articles) {
		Articles article = this.getHibernateTemplate().get(Articles.class, articles.getId());
		if(null != article) {
			this.getHibernateTemplate().delete(article);
		}
	}

	


}
