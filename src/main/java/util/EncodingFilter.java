package util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �������봦��
 * 
 */
public class EncodingFilter implements Filter {
	
	private String encoding = "UTF-8";// Ĭ���ַ���
	
	public EncodingFilter() {
		super();
	}
	
	public void destroy() {}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// ���Ĵ�������ŵ� chain.doFilter(request, response)����ǰ��
		res.setContentType("text/html;charset=" + this.encoding);
		if (req.getMethod().equalsIgnoreCase("post")) {
			req.setCharacterEncoding(this.encoding);
		} else {
			@SuppressWarnings("rawtypes")
			Map map = req.getParameterMap();// �������в�����=����ֵ(����)��Map����
			@SuppressWarnings("rawtypes")
			Set set = map.keySet();// ȡ�����в�����
			@SuppressWarnings("rawtypes")
			Iterator it = set.iterator();
			while (it.hasNext()) {
				String name = (String) it.next();
				String[] values = (String[]) map.get(name);// ȡ������ֵ[ע������ֵΪһ������]
				for (int i = 0; i < values.length; i++) {
					values[i] = new String(values[i].getBytes("ISO-8859-1"),
							this.encoding);
				}
			}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String s = filterConfig.getInitParameter("encoding");// ��ȡweb.xml�ļ������õ��ַ���
		if (null != s && !s.trim().equals("")) {
			this.encoding = s.trim();
		}
	}

}
