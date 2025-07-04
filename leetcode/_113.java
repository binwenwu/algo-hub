import java.util.ArrayList;
import java.util.List;

public class _113 {
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

    // 递归1
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        dfs(root, targetSum, path);
        return res;
    }

    private void dfs(TreeNode node, int targetSum, List<Integer> path) {
        path.add(node.val);

        if (node.left == null && node.right == null) {
            if (node.val == targetSum) {
                res.add(path);
            } else {
                return;
            }
        }
        if (node.left != null) {
            dfs(node.left, targetSum - node.val, new ArrayList<>(path)); // 这个地方可以用回溯，避免一直创建新的list
        }

        if (node.right != null) {
            dfs(node.right, targetSum - node.val, new ArrayList<>(path)); // 这个地方可以用回溯，避免一直创建新的list
        }
    }

    // 递归2
    List<List<Integer>> res2 = new ArrayList<>();

    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return res2;
        }
        List<Integer> path = new ArrayList<>();
        preDfs(root, targetSum, path);
        return res2;
    }

    private void preDfs(TreeNode node, int targetSum, List<Integer> path) {
        path.add(node.val);

        if (node.left == null && node.right == null) {
            if (node.val == targetSum) {
                res2.add(new ArrayList<>(path));
            } else {
                return;
            }
        }
        if (node.left != null) {
            preDfs(node.left, targetSum - node.val, path);
            path.remove(path.size() - 1); // 回溯
        }

        if (node.right != null) {
            preDfs(node.right, targetSum - node.val, path);
            path.remove(path.size() - 1); // 回溯
        }
    }
}
