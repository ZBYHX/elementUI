package sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;

import base.action.BaseAction;
import sys.entity.TreeNode;
import sys.service.ITreeNodeService;

public class TreeNodeAction extends BaseAction implements ModelDriven<TreeNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7311957222736826442L;
	
	private TreeNode treeNode = new TreeNode();
	private ITreeNodeService treeNodeService;
	
	public TreeNodeAction() {
		super();
	}

	@Override
	public TreeNode getModel() {
		return treeNode;
	}

	public TreeNode getTreeNode() {
		return treeNode;
	}

	public void setTreeNode(TreeNode treeNode) {
		this.treeNode = treeNode;
	}

	public ITreeNodeService getTreeNodeService() {
		return treeNodeService;
	}

	public void setTreeNodeService(ITreeNodeService treeNodeService) {
		this.treeNodeService = treeNodeService;
	}
	
	public String selectAllTreeNodes() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TreeNode> list = treeNodeService.selectALLTreeNode();
//		for(TreeNode t : list) {
//			System.out.println("节点名称：" + t.getTree_node_name());
//			System.out.println("对应的子节点长度：" + t.getChildNodes().size());
//			for(TreeNode t2 : t.getChildNodes()) {
//				System.out.println(t2);
//			}
//		}
		map.put("code", "0");
		map.put("message", "查询信息成功！");
		map.put("results", list);
		
		this.writeJson(map);
		
		return null;
	}
	

	public String selectOneTreeNode() {
		Map<String, Object> map = new HashMap<String, Object>();
		TreeNode t = treeNodeService.selectOneTreeNode(treeNode);
		map.put("treeNode", t);
//		map.put("childrenNodes", t.getChildNodes());
		this.writeJson(map);
		
		for(TreeNode t2 : t.getChildNodes()) {
			System.out.println(t2);
		}
		
		return null;
	}
	
	

}
