import java.util.LinkedList;
import java.util.Queue;

public class _662 {

    class Pair {
        TreeNode node;
        int index;

        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    /**
     * 一个编号为 index 的左子节点的编号记为 2×index，右子节点的编号记为 2×index+1，
     * 计算每层宽度时，用每层节点的最大编号减去最小编号再加 1 即为宽度
     */
    public int widthOfBinaryTree(TreeNode root) {

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 1));

        int maxWidth = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            int first = queue.peek().index;
            int last = first;

            for (int i = 0; i < size; i++) {

                Pair cur = queue.poll();
                TreeNode node = cur.node;
                int index = cur.index;

                last = index;

                if (node.left != null)
                    queue.offer(new Pair(node.left, index * 2));

                if (node.right != null)
                    queue.offer(new Pair(node.right, index * 2 + 1));
            }

            maxWidth = Math.max(maxWidth, (int) (last - first + 1));
        }

        return maxWidth;
    }

}
