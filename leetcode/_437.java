import java.util.HashMap;
import java.util.Map;

public class _437 {
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

    private int ans;

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0L, 1);
        dfs(root, 0, targetSum, cnt);
        return ans;
    }

    private void dfs(TreeNode node, long sum, int targetSum, Map<Long, Integer> cnt) {
        if (node == null) {
            return;
        }

        sum += node.val;
        // 把 node 当作路径的终点，统计有多少个起点
        ans += cnt.getOrDefault(sum - targetSum, 0);
        cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
        dfs(node.left, sum, targetSum, cnt);
        dfs(node.right, sum, targetSum, cnt);
        cnt.put(sum, cnt.get(sum) - 1);
    }
}
