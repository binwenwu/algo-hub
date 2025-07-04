import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _429 {
    public static void main(String[] args) {

    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };


    // 方法1，层序遍历
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int currentLevelSize = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < currentLevelSize; i++) {
                Node node = queue.poll();
                level.add(node.val);
                for (Node n : node.children) {
                    if (n != null) {
                        queue.offer(n);
                    }
                }
            }
            res.add(level);
        }

        return res;
    }
}
