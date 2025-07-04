import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _144 {
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
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    private void preOrder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

    // 迭代法 1
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root == null) {
            return list;
        }

        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode temp = deque.removeLast();
            list.add(temp.val);
            if (temp.right != null) {
                deque.addLast(temp.right);
            }
            if (temp.left != null) {
                deque.addLast(temp.left);
            }
        }

        return list;
    }

    // 迭代法 2
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                res.add(node.val);
                stack.add(node);
                node = node.left;
            }
            node = stack.removeLast();
            node = node.right;
        }
        return res;
    }

}
