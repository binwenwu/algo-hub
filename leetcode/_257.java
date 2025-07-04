import java.util.ArrayList;
import java.util.List;

public class _257 {
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

    // dfs 遍历
    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        String begin = "";
        add(root, begin, res);
        return res;
    }

    private void add(TreeNode node, String road, List<String> res) {
        if (node.left == null && node.right == null) {
            res.add(road + node.val);
            return;
        }
        if (node.left != null) {
            add(node.left, (road + node.val + "->"), res);
        }
        if (node.right != null) {
            add(node.right, (road + node.val + "->"), res);
        }
    }

}
