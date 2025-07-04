import java.util.Deque;
import java.util.LinkedList;

public class _538 {
    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {

        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 递归：反中序遍历二叉树，每次都与前面一个节点的值累加即可
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        convertBST(root.right);
        sum = sum + root.val;
        root.val = sum;
        convertBST(root.left);

        return root;
    }
    
    // 迭代法
    public TreeNode convertBST2(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return root;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.offer(node);
                node = node.right;
            }
            node = stack.pollLast();
            sum = node.val + sum;
            node.val = sum;
            node = node.left;
        }

        return root;
    }



}
