package sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 树节点信息的实体类
 * @author Lemons
 *
 */
//@SuppressWarnings("deprecation")
//@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class TreeNode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5902727975424528195L;
	private Integer tree_node_id;//节点ID
	private String tree_node_name;//节点名称
	private Integer tree_node_type;//节点类型
//	private Integer parent_node_id;//父节点
	private String url;//URL
	private Integer position;//位置
	private String icon;//节点图标
	
//	@JsonIgnore
	private List<TreeNode> childNodes = new ArrayList<TreeNode>();//子节点集合
	@JsonIgnore
	private TreeNode parentNode;//父节点
	
	private Integer initChileNodes = 1;
	
	public TreeNode() {
		super();
	}

	public Integer getTree_node_id() {
		return tree_node_id;
	}

	public void setTree_node_id(Integer tree_node_id) {
		this.tree_node_id = tree_node_id;
	}

	public String getTree_node_name() {
		return tree_node_name;
	}

	public void setTree_node_name(String tree_node_name) {
		this.tree_node_name = tree_node_name;
	}

	public Integer getTree_node_type() {
		return tree_node_type;
	}

	public void setTree_node_type(Integer tree_node_type) {
		this.tree_node_type = tree_node_type;
	}

//	public Integer getParent_node_id() {
//		return parent_node_id;
//	}
//
//	public void setParent_node_id(Integer parent_node_id) {
//		this.parent_node_id = parent_node_id;
//	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<TreeNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(List<TreeNode> childNodes) {
		this.childNodes = childNodes;
	}

	public TreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
	}
	
	public Integer getInitChileNodes() {
		return initChileNodes;
	}

	public void setInitChileNodes(Integer initChileNodes) {
		this.initChileNodes = initChileNodes;
	}

	@Override
	public String toString() {
		return "TreeNode [tree_node_id=" + tree_node_id + ", tree_node_name=" + tree_node_name + ", tree_node_type="
				+ tree_node_type + ", url=" + url + ", position=" + position
				+ ", icon=" + icon + ", childNodes=" + childNodes + "]";
	}
	
	

}
