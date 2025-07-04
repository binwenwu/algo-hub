import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _501 {
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

    // 方法一，普通递归，并使用额外存储空间
    Map<Integer, Integer> temp = new HashMap<>();
    int maxCount = 0;
    List<Integer> list = new ArrayList<>();

    public int[] findMode1(TreeNode root) {
        helper(root);
        for (int i : temp.values()) {
            if (i > maxCount) {
                maxCount = i;
            }
        }
        for (Map.Entry<Integer, Integer> entry : temp.entrySet()) {
            if (entry.getValue() == maxCount) {
                list.add(entry.getKey());
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    private void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        temp.put(node.val, temp.getOrDefault(node.val, 0) + 1);
        helper(node.left);
        helper(node.right);
    }

    // 方法二，不使用额外空间，且一次中序遍历
    int last = 0;
    int max = 0;
    int temp_count = 0;
    private List<Integer> result = new ArrayList<>();

    public int[] findMode2(TreeNode root) {
        helper2(root);
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    private void helper2(TreeNode node) {
        if (node == null) {
            return;
        }
        helper2(node.left);

        int node_val = node.val;
        if (node_val != last) {
            temp_count = 1; // 第一次遇到
        } else {
            temp_count++;
        }
        if (temp_count > max) {
            // 这里每次加入都清空，就可以做到一次中序遍历即可
            result.clear();
            result.add(node_val);
            max = temp_count;
        } else if (temp_count == max) {
            result.add(node_val);
        }

        last = node_val;
        helper2(node.right);
    }
}
