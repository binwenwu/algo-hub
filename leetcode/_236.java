
public class _236 {
    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 自己想的笨蛋方法，每一轮都会在左右子树找p.val和q.val
    /**
     * 这种做法的问题在于：helper() 每次都要遍历整棵子树，
     * 从而带来了 重复遍历、时间复杂度过高（接近 O(n²)） 的问题。
     */
    boolean b1 = false;
    boolean b2 = false;
    boolean b3 = false;
    boolean b4 = false;
    TreeNode res = null;

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        b1 = helper(root.left, p.val);
        b2 = helper(root.left, q.val);
        b3 = helper(root.right, p.val);
        b4 = helper(root.right, q.val);
        if ((b1 || b2) && (b3 || b4)) {
            return root;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        if ((b1 || b2)) {
            return lowestCommonAncestor1(root.left, p, q);
        } else {
            return lowestCommonAncestor1(root.right, p, q);
        }
    }

    private boolean helper(TreeNode node, int target) {
        if (node == null) {
            return false;
        }
        if (node.val == target) {
            return true;
        }
        return helper(node.right, target) || helper(node.left, target);
    }

    // 方法二：从下往上递归回溯，只遍历一遍整棵树，时间复杂度 O(n)。
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) {
            return root;
        }

        // 递归查找左右子树
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        // 如果左右都不为空，说明当前 root 是最近公共祖先
        if (left != null && right != null) {
            return root;
        }

        // 否则返回非空的一边
        return left != null ? left : right;
    }

}
