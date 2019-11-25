package util;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 	ͨ�÷�ҳ������
 * 
 */
public class PageBean {
	//��������
	private int current_page = 1;//��ǰҳ
	private int page_size = 10;// ÿҳ��ʾ��
	private int total_count = 12;//�ܼ�¼��
	private boolean pageination = true;//�Ƿ��ҳ
	
	private String url;//��ȡ��һ������·��
	private Map<String, String[]> map;//��ȡ��һ������Ĳ���
	
	//�����޲ι�����вι���ķ���
	public PageBean() {}
	
//	public PageBean(int current_page, int page_size,HttpServletRequest request) {
//		this.current_page = current_page;
//		this.page_size = page_size; 
//		this.url = request.getRequestURI();//��ȡ��һ������·��
//		this.map = request.getParameterMap();//��ȡ��һ������Ĳ���
//	}

	//get��set����
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
		this.url = url;//��ȡ��һ������·��
	}

	public Map<String, String[]> getMap() {
		return map;
	}

	public void setMap(Map<String, String[]> map) {
		this.map = map;//��ȡ��һ������Ĳ���
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
	
	
	//tostring�ķ���
	@Override
	public String toString() {
		return "PageBean [current_page=" + current_page + ", page_size=" + page_size + ", total_count=" + total_count
				+ ", pageination=" + pageination + "]";
	}
	
	/**
	 * ������ҳ�������ҳ����
	 */
	public int getMaxPage() {
		//�ܼ�¼��%ÿҳ��ʾ�ļ�¼�� == 0���ܼ�¼��/ÿҳ��ʾ�ļ�¼�� : �ܼ�¼��/ÿҳ��ʾ�ļ�¼��+1
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
	 * ��һҳ
	 */
	public int getPivePage() {
		//�����ǰҳ������1��˵����ǰҳ�������ڵ�2ҳ�������һҳ���ص���1ҳ
		if(this.current_page > 1) {
			return this.current_page-1;
		}
		return 1;
	}
	
	/**
	 * ��һҳ
	 */
	public int getNextPage() {
		//�����ǰҳ������1��˵����ǰҳ�������ڵ�2ҳ�������һҳ���ص���1ҳ
		if(this.current_page < getMaxPage()) {
			return this.current_page+1;
		}
		return getMaxPage();
	}
	
	/**
	 * ���ҳÿҳ����ʼλ��  ����ÿҳ��ʾ10������
	 * ��ǰҳ1  1~10
	 * ��ǰҳ2  11~20
	 * ��ǰҳ3  21~30
	 * ÿҳ��ʼ�±꣺����ǰҳ-1��* ÿҳ��ʾ��¼��+1
	 * ÿҳ�����±꣺ ��ǰҳ * ÿҳ��ʾ��¼��
	 * 
	 */
	public int getStartIndex() {
		//ע�⣺����Ҫ�ټ�1����Ϊlimit�Ǵ�0��ʼ���
		return (this.current_page-1)*this.page_size;
	}
	
	
	//����request
	public void setRequest(HttpServletRequest request) {
		//���ò���
		String current_page = request.getParameter("current_page");
		String page_size = request.getParameter("page_size");
		String pageination = request.getParameter("pageination");
		this.setCurrent_page(current_page);
		this.setPage_size(page_size);
		this.setPageination(pageination);
		
		//��ȡ��һ�������·��
		this.url = request.getRequestURI();
		//��ȡ��һ�ε�����·��
		this.map = request.getParameterMap();
		
//		System.out.println(current_page);
		
	}
	
	
	
	
	
}
