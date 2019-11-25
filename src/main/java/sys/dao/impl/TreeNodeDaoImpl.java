package sys.dao.impl;

import java.util.List;
import base.dao.BaseDao;
import sys.dao.ITreeNodeDao;
import sys.entity.TreeNode;

public class TreeNodeDaoImpl extends BaseDao implements ITreeNodeDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = 738882862501587474L;
	
	public TreeNodeDaoImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TreeNode> selectALLTreeNode() {
		String hql = "from TreeNode t where t.parentNode is null";
		
		return this.executeQuery(hql, null, null);
	}

	@Override
	public TreeNode selectOneTreeNode(TreeNode treeNode) {
		TreeNode t = this.getHibernateTemplate().get(TreeNode.class, treeNode.getTree_node_id());
		if(new Integer(1).equals(treeNode.getInitChileNodes())) {
			this.getHibernateTemplate().initialize(t.getChildNodes());
		}
		return t;
	}
	
	

}
