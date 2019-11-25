package base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import util.PageBean;

public abstract class BaseDao extends HibernateDaoSupport implements Serializable {
	private static final long serialVersionUID = -2832692964328823554L;
	
	private Logger log = LogManager.getLogger(BaseDao.class);
	
	public BaseDao() {
		super();
	}
	
	/**
	 * 通用的赋值方式
	 * @param query
	 * @param args
	 */
	@SuppressWarnings("rawtypes")
	public void setParameters(Query query, Map<String, Object> args) {
		if(null == args || 0 == args.size()) {
			return;
		}
		log.info("hql的查询参数为：" + args);
		
		String name = null;
		Object value = null;
		for(Map.Entry<String, Object> entry : args.entrySet()) {
			name = entry.getKey();//min
			value = entry.getValue();//1000f
			if(value instanceof Collection) {//List和Set
				query.setParameterList(name, (Collection) value);//对集合赋值
			} else if(value instanceof Object[]){
				query.setParameterList(name, (Object[]) value);//对数组赋值
			} else {
				query.setParameter(name, value);
			}
		}
	}
	
	/**
	 * 查询全部(包含条件查询)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List executeQuery(final String hql,final Map<String, Object> args, final PageBean pageBean) {
		return this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				//1、查满足条件的总记录数
				Query query = null;
				if(null != pageBean && pageBean.isPageination()) {
					String countHql = getCountHql(hql);
					log.info("countHql--01[ " + countHql + " ]");
					query = session.createQuery(countHql);
					setParameters(query, args);//给占位符赋值
					Object total = query.uniqueResult();
					pageBean.setTotal_count(Integer.parseInt(total.toString()));
					
				}
				log.info("User  Hql--02[ " + hql + " ]");
				//2、查满足并指定页码的记录
				query = session.createQuery(hql);
				if(null != pageBean && pageBean.isPageination()) {
					query.setFirstResult(pageBean.getStartIndex());
					query.setMaxResults(pageBean.getPage_size());
				}
				setParameters(query, args);//给占位符赋值
				List list = query.list();
				
				return list;
			}
		});
	}
	
	
	/**
	 * 将普通的hql转换成查总记录数的hql
	 */
	public static String getCountHql(String hql) {
		String newHql = new String(hql).toUpperCase();
		
		int start = newHql.indexOf("FROM ");
		int end = newHql.indexOf("ORDER BY");
		if(-1 == end){
			newHql = "select count(*) "+hql.substring(start);
		} else {
			newHql = "select count(*) "+hql.substring(start, end);
		}
		
		return newHql;
	}
	
	
	
	/**
	 * 根据条件查询单个数据
	 * @param hql
	 * @param args
	 * @return
	 */
	public Object queryOne(final String hql, final Map<String, Object> args) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("rawtypes")
				Query query = null;
				
				query = session.createQuery(hql);
				setParameters(query, args);//给占位符赋值
				
				Object obj = query.uniqueResult();
				
				return obj;
			}
		});
	}
	
	
	
}
