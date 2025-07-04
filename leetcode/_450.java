
public class _450 {
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

    // 方法一：自己想的迭代方法
    public TreeNode deleteNode1(TreeNode root, int key) {
        TreeNode virtual = new TreeNode(100001);
        virtual.left = root;
        virtual.right = null;
        TreeNode node = virtual;
        TreeNode pre = null;
        TreeNode right = null;
        while (node != null) {
            if (key < node.val) {
                pre = node;
                node = node.left;
            } else if (key > node.val) {
                pre = node;
                node = node.right;
            } else {
                if (node.left != null && node.right != null) {
                    if (node.val < pre.val) {
                        pre.left = node.right;
                    } else {
                        pre.right = node.right;
                    }
                    right = node.right;
                    while (right.left != null) {
                        right = right.left;
                    }
                    right.left = node.left;
                } else if (node.left != null) {
                    if (node.val < pre.val) {
                        pre.left = node.left;
                    } else {
                        pre.right = node.left;
                    }
                } else {
                    if (node.val < pre.val) {
                        pre.left = node.right;
                    } else {
                        pre.right = node.right;
                    }
                }
                break;
            }
        }

        return virtual.left;
    }

    // 方法一的优化
    public TreeNode deleteNode2(TreeNode root, int key) {
        TreeNode virtual = new TreeNode(100001); // 虚拟根节点
        virtual.left = root;

        TreeNode pre = virtual;
        TreeNode node = root;
        boolean isLeft = true;// 用于记当前 node 是 pre 的左还是右节点

        while (node != null) {
            if (key < node.val) {
                pre = node;
                node = node.left;
                isLeft = true;
            } else if (key > node.val) {
                pre = node;
                node = node.right;
                isLeft = false;
            } else {
                // 找到要删除的节点
                if (node.left != null && node.right != null) {
                    TreeNode right = node.right;
                    while (right.left != null) {
                        right = right.left;
                    }
                    right.left = node.left;
                    if (isLeft) {
                        pre.left = node.right;
                    } else {
                        pre.right = node.right;
                    }
                } else {
                    TreeNode child = (node.left != null) ? node.left : node.right;
                    if (isLeft) {
                        pre.left = child;
                    } else {
                        pre.right = child;
                    }
                }
                break;
            }
        }

        return virtual.left;
    }

}
