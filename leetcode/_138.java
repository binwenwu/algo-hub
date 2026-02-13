import java.util.HashMap;
import java.util.Map;

public class _138 {
    public static void main(String[] args) {

    }

    public class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        if (map.containsKey(head)) {
            return map.get(head);
        } else {
            Node node = new Node(head.val);
            map.put(head, node);
            node.next = copyRandomList(head.next);
            node.random = copyRandomList(head.random);
            return node;
        }

    }
}
