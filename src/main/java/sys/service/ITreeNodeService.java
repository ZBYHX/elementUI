package sys.service;

import java.util.List;

import sys.entity.TreeNode;

public interface ITreeNodeService {
	
	//查询所有节点
	public List<TreeNode> selectALLTreeNode();
	
	//查询单个节点
	public TreeNode selectOneTreeNode(TreeNode treeNode);
	
}
