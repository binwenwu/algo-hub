import java.util.ArrayList;
import java.util.List;

public class _589 {
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

    public List<Integer> preorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        pre(root, res);
        return res;
    }

    private void pre(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        for (Node n : node.children) {
            pre(n, list);
        }
    }
}
