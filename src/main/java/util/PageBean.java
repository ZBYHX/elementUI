package util;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 	通用分页工具类
 * 
 */
public class PageBean {
	//定义属性
	private int current_page = 1;//当前页
	private int page_size = 10;// 每页显示数
	private int total_count = 12;//总记录数
	private boolean pageination = true;//是否分页
	
	private String url;//获取上一次请求路径
	private Map<String, String[]> map;//获取上一次请求的参数
	
	//定义无参构造和有参构造的方法
	public PageBean() {}
	
//	public PageBean(int current_page, int page_size,HttpServletRequest request) {
//		this.current_page = current_page;
//		this.page_size = page_size; 
//		this.url = request.getRequestURI();//获取上一次请求路径
//		this.map = request.getParameterMap();//获取上一次请求的参数
//	}

	//get和set方法
	public int getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
	public int getPage_size() {
		return page_size;
	}
	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public boolean isPageination() {
		return pageination;
	}
	public void setPageination(boolean pageination) {
		this.pageination = pageination;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;//获取上一次请求路径
	}

	public Map<String, String[]> getMap() {
		return map;
	}

	public void setMap(Map<String, String[]> map) {
		this.map = map;//获取上一次请求的参数
	}
	
	public void setCurrent_page(String current_page) {
		if(current_page!=null) {
			this.current_page = Integer.parseInt(current_page);
		}
	}
	public void setPage_size(String page_size) {
		if(page_size!=null) {
			this.page_size = Integer.parseInt(page_size);
		}
	}
	public void setPageination(String pageination) {
		if(pageination!=null) {
			this.pageination = Boolean.parseBoolean(pageination);
		}
	}
	
	
	//tostring的方法
	@Override
	public String toString() {
		return "PageBean [current_page=" + current_page + ", page_size=" + page_size + ", total_count=" + total_count
				+ ", pageination=" + pageination + "]";
	}
	
	/**
	 * 计算总页数（最大页数）
	 */
	public int getMaxPage() {
		//总记录数%每页显示的记录数 == 0？总记录数/每页显示的记录数 : 总记录数/每页显示的记录数+1
		int max = this.total_count/this.page_size;
		if(this.total_count%this.page_size != 0) {
			max ++;
		}
		if(this.total_count<=this.page_size) {
			max = 1;
		}
		return max;
	}
	
	/**
	 * 上一页
	 */
	public int getPivePage() {
		//如果当前页数大于1，说明当前页数至少在第2页，点击上一页返回到第1页
		if(this.current_page > 1) {
			return this.current_page-1;
		}
		return 1;
	}
	
	/**
	 * 下一页
	 */
	public int getNextPage() {
		//如果当前页数大于1，说明当前页数至少在第2页，点击上一页返回到第1页
		if(this.current_page < getMaxPage()) {
			return this.current_page+1;
		}
		return getMaxPage();
	}
	
	/**
	 * 求分页每页的起始位置  假如每页显示10条数据
	 * 当前页1  1~10
	 * 当前页2  11~20
	 * 当前页3  21~30
	 * 每页起始下标：（当前页-1）* 每页显示记录数+1
	 * 每页结束下标： 当前页 * 每页显示记录数
	 * 
	 */
	public int getStartIndex() {
		//注意：不需要再加1，因为limit是从0开始算的
		return (this.current_page-1)*this.page_size;
	}
	
	
	//设置request
	public void setRequest(HttpServletRequest request) {
		//设置参数
		String current_page = request.getParameter("current_page");
		String page_size = request.getParameter("page_size");
		String pageination = request.getParameter("pageination");
		this.setCurrent_page(current_page);
		this.setPage_size(page_size);
		this.setPageination(pageination);
		
		//获取上一次请求的路径
		this.url = request.getRequestURI();
		//获取上一次的请求路径
		this.map = request.getParameterMap();
		
//		System.out.println(current_page);
		
	}
	
	
	
	
	
}
