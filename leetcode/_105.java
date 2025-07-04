import java.util.HashMap;
import java.util.Map;

public class _105 {
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

    // 递归的方法
    Map<Integer, Integer> inorderIndexMap = new HashMap<>();
    int preIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        preIndex = 0;
        return helper(preorder, inorder, 0, n - 1);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int in_left, int in_right) {
        if (in_left > in_right) {
            return null;
        }
        int root_val = preorder[preIndex];
        preIndex++;
        TreeNode root = new TreeNode(root_val);

        int index = inorderIndexMap.get(root_val);
        // 注意这里的顺序，先左子树，后右子树，因为在preorder中，左子树的根节点会先出现，
        // 同理，106这道题，就必须先右子树，因为，post后续遍历，左->右->中，postIndex是从后往前，所以得先右子树
        root.left = helper(preorder, inorder, in_left, index - 1);
        root.right = helper(preorder, inorder, index + 1, in_right);
        return root;
    }
}
