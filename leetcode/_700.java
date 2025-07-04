
public class _700 {
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

    // 普通的递归方式
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else {
            TreeNode node = searchBST1(root.left, val);
            if (node != null) {
                return node;
            } else {
                return searchBST1(root.right, val);
            }
        }
    }

    // 有大小比较的递归
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else {
            return val > root.val ? searchBST(root.right, val) : searchBST(root.left, val);
        }
    }

    


}
