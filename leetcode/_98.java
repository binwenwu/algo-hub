
public class _98 {
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

    // 方法一： 递归判断
    public boolean isValidBST1(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean helper(TreeNode node, long lower, long upper) {
        if (node == null)
            return true;

        if (node.val <= lower || node.val >= upper) {
            return false;
        }

        // 左子树的 upper 限制是当前节点值，右子树的 lower 限制是当前节点值
        return helper(node.left, lower, node.val) && helper(node.right, node.val, upper);
    }

    // 方法二：
    // 要知道中序遍历下，输出的二叉搜索树节点的数值是有序序列
    // 有了这个特性，验证二叉搜索树，就相当于变成了判断一个序列是不是递增的了
    long prev = Long.MIN_VALUE;

    public boolean isValidBST2(TreeNode root) {
        return inorder(root);
    }

    private boolean inorder(TreeNode node) {
        if (node == null)
            return true;

        if (!inorder(node.left))
            return false;

        if (node.val <= prev)
            return false;
        prev = node.val;

        return inorder(node.right);
    }

}
