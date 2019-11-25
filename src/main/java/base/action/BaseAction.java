package base.action;

import java.io.PrintWriter;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import util.PageBean;

public abstract class BaseAction implements Serializable {
	private static final long serialVersionUID = -131622575427812768L;
	
	public BaseAction() {
		super();
	}
	
	public String writeJson(Object obj) {
		try {
			//1、Java对象=>json字符串
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(obj);
			
			//2、向客户端输出json字符串
			HttpServletResponse response = ServletActionContext.getResponse();
			//如果json打印的时候出现乱码，设置响应的字符集和内容类型即可
			response.setCharacterEncoding("utf-8");//设置编码格式
			response.setContentType("application/json;charset=utf-8");//设置内容类型
			
			PrintWriter out = response.getWriter();
			out.print(json);
			
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public PageBean createPageBean() {
		HttpServletRequest request = ServletActionContext.getRequest();
		PageBean pageBean = new PageBean();
		pageBean.setRequest(request);
		return pageBean;
	}

}
