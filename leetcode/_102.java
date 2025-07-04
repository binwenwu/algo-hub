import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _102 {
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

    // 自己的写法，用list实现
    public List<List<Integer>> levelOrder1(TreeNode root) {
        TreeNode node = root;
        List<List<Integer>> res = new ArrayList<>();
        if (node == null) {
            return res;
        }

        List<TreeNode> nodeList = new ArrayList<>(); // 存储每一层节点

        nodeList.add(node);
        while (!nodeList.isEmpty()) {
            List<Integer> valList = new ArrayList<>();
            List<TreeNode> tempList = new ArrayList<>(); // 存储每一层节点
            for (TreeNode td : nodeList) {
                valList.add(td.val);
                if (td.left != null) {
                    tempList.add(td.left);
                }
                if (td.right != null) {
                    tempList.add(td.right);
                }
            }
            res.add(valList);
            nodeList = tempList;
        }

        return res;
    }

    // 队列实现，迭代
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(level);
        }

        return res;
    }

}
