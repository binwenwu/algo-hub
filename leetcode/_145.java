import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class _145 {
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

    // 递归法
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postOrder(root, list);
        return list;
    }

    private void postOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.val);
    }

    // 迭代法 1，先序遍历里面改变left和right的顺序，然后再把list反转
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root == null) {
            return list;
        }


        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode temp = deque.removeLast();
            list.add(temp.val);
            if (temp.left != null) {
                deque.addLast(temp.left);
            }
            if (temp.right != null) {
                deque.addLast(temp.right);
            }
        }

        Collections.reverse(list);// 翻转以下 list
        return list;
    }

    // 迭代法 2，结果不翻转
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque();
        TreeNode node = root;
        TreeNode prev = null;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.add(node);
                node = node.left;
            }
            node = stack.removeLast();
            if (node.right == null || node.right == prev) {
                res.add(node.val);
                prev = node;
                node = null;
            } else {
                stack.add(node);
                node = node.right;
            }
        }
        return res;
    }
}
