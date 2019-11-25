package test1;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sys.entity.TreeNode;
import sys.service.ITreeNodeService;

public class TreeNodeServiceTest extends BaseTestCase {
	
	private ITreeNodeService treeNodeService = null;
	private TreeNode treeNode = null;
	
	@Before
	public void setUp() throws Exception {
		System.out.println("测试前");
		treeNodeService = (ITreeNodeService) this.getBean("treeNodeService");
		treeNode = new TreeNode();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("测试后");
	}

	@Test
	public void testSelectALLTreeNode() {
		List<TreeNode> list = treeNodeService.selectALLTreeNode();
		for(TreeNode t : list) {
			System.out.println(t);
		}
	}

	@Test
	public void testSelectOneTreeNode() {
		treeNode.setTree_node_id(1);
		TreeNode t = treeNodeService.selectOneTreeNode(treeNode);
		System.out.println("查询的当前树节点信息："+t);
		System.out.println("查询得到的相应子节点数量："+t.getChildNodes().size());
		for(TreeNode t2 : t.getChildNodes()) {
			System.out.println(t2);
		}
	}
	
	

}
