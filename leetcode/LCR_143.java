public class LCR_143 {
    // 递归
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false; // 题目规定：空树不会是以 tree1 的某个节点为根的子树具有 相同的结构和节点值
        }
        if (A == null) {
            return false; // A 为空， 说明不可能匹配上
        }

        return match(A, B)
                || isSubStructure(A.left, B)
                || isSubStructure(A.right, B);
    }

    private boolean match(TreeNode a, TreeNode b) {
        if (b == null) // b 已经匹配完了
            return true;
        if (a == null || a.val != b.val)
            return false;

        return match(a.left, b.left) && match(a.right, b.right);
    }
}

class TreeNode {
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