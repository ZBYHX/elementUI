package sys.entity;

import java.io.Serializable;
/**
 * 文章类的实体类
 * @author Lemons
 *
 */
public class Articles implements Serializable {
	private static final long serialVersionUID = 1343403129890831686L;
	
	private Integer id;//文章ID
	private String title;//文章标题
	private String body;//文章内容
	
	public Articles() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Articles [id=" + id + ", title=" + title + ", body=" + body + "]";
	}
	
	
	
}
