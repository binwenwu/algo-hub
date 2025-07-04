import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _559 {
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

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        int num = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            num = num + 1;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (node.children != null) {
                    for (Node n : node.children) {
                        queue.offer(n);
                    }
                }
            }
        }

        return num;
    }
}
