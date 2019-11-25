package sys.service.impl;

import java.util.List;

import base.service.BaseService;
import sys.dao.ITreeNodeDao;
import sys.entity.TreeNode;
import sys.service.ITreeNodeService;

public class TreeNodeServiceImpl extends BaseService implements ITreeNodeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5924918909048588351L;
	
	private ITreeNodeDao treeNodeDao;
	
	public TreeNodeServiceImpl() {
		super();
	}
	
	public ITreeNodeDao getTreeNodeDao() {
		return treeNodeDao;
	}

	public void setTreeNodeDao(ITreeNodeDao treeNodeDao) {
		this.treeNodeDao = treeNodeDao;
	}

	@Override
	public List<TreeNode> selectALLTreeNode() {
		// TODO Auto-generated method stub
		return treeNodeDao.selectALLTreeNode();
	}

	@Override
	public TreeNode selectOneTreeNode(TreeNode treeNode) {
		// TODO Auto-generated method stub
		return treeNodeDao.selectOneTreeNode(treeNode);
	}

}
