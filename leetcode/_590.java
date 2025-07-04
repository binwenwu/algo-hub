import java.util.ArrayList;
import java.util.List;

public class _590 {
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

    public List<Integer> postorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        post(root, res);
        return res;
    }

    private void post(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }

        for (Node n : node.children) {
            post(n, list);
        }

        list.add(node.val);
    }
}
