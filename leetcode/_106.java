import java.util.HashMap;
import java.util.Map;

public class _106 {
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
    int postIndex;

    public TreeNode buildTree1(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        postIndex = n - 1;
        return helper(inorder, postorder, 0, n - 1);
    }

    private TreeNode helper(int[] inorder, int[] postorder, int in_left, int in_right) {
        if (in_left > in_right) {
            return null;
        }
        int root_val = postorder[postIndex];
        postIndex--;
        TreeNode root = new TreeNode(root_val);

        int index = inorderIndexMap.get(root_val);
        root.right = helper(inorder, postorder, index + 1, in_right);
        root.left = helper(inorder, postorder, in_left, index - 1);
        return root;
    }

    // 迭代

}
