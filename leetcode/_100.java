import java.util.Deque;
import java.util.LinkedList;

public class _100 {
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

    // 用两个栈进行层序遍历
    public boolean isSameTree1(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        Deque<TreeNode> stack_p = new LinkedList<>();
        Deque<TreeNode> stack_q = new LinkedList<>();
        stack_p.add(p);
        stack_q.add(q);

        while (!stack_p.isEmpty() || !stack_q.isEmpty()) {
            int p_size = stack_p.size();

            for (int i = 0; i < p_size; i++) {
                TreeNode p_node = stack_p.removeLast();
                TreeNode q_node = stack_q.removeLast();
                if (q_node == null && p_node == null) {
                    continue;
                }

                if (p_node == null || q_node == null) {
                    return false;
                }

                if (p_node.val != q_node.val) {
                    return false;
                }

                stack_p.add(p_node.left);
                stack_p.add(p_node.right);
                stack_q.add(q_node.left);
                stack_q.add(q_node.right);

            }
        }

        return true;
    }

    // 或者采用深度优先遍历，递归的写法
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);
        }
    }

}
