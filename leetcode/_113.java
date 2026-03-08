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

    /**
     * 1 进入节点
     * 2 加入路径
     * 3 更新路径和
     * 4 如果是叶子并满足条件 → 加入答案
     * 5 递归左右子树
     * 6 回溯（删除路径最后一个元素）
     */
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return res;
    }

    private void dfs(TreeNode node, int target) {
        if (node == null)
            return;

        path.add(node.val);

        if (node.left == null && node.right == null && target == node.val) {
            res.add(new ArrayList<>(path));
            path.remove(path.size() - 1); // 回溯
            return;
        } else {
            dfs(node.left, target - node.val);
            dfs(node.right, target - node.val);
            path.remove(path.size() - 1); // 回溯
        }
    }
}
