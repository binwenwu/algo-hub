import java.util.Deque;
import java.util.LinkedList;

public class _572 {
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

    // 广度优先遍历，每个节点都拿去和subRoot比较是否相同
    public boolean isSubtree1(TreeNode root, TreeNode subRoot) {
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.removeLast();
            if (isSameTree(node, subRoot)) {
                return true;
            }
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return false;
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    // 先序遍历的方法
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null)
            return false;
        if (isSameTree2(s, t))
            return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree2(TreeNode s, TreeNode t) {
        if (s == null && t == null)
            return true;
        if (s == null || t == null || s.val != t.val)
            return false;
        return isSameTree2(s.left, t.left) && isSameTree2(s.right, t.right);
    }

}
