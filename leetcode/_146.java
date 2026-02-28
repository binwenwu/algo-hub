import java.util.HashMap;
import java.util.Map;

public class _146 {
    public static void main(String[] args) {

    }
}

class LRUCache {
    private static class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private final int capacity;
    private final Node dummy = new Node(0, 0); // 哨兵节点
    private final Map<Integer, Node> keyToNode = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public int get(int key) {
        Node node = getNode(key); // getNode 会把对应节点移到链表头部
        return node != null ? node.value : -1;
    }

    public void put(int key, int value) {
        Node node = getNode(key); // getNode 会把对应节点移到链表头部
        if (node != null) { // 有这本书
            node.value = value; // 更新 value
            return;
        }
        node = new Node(key, value); // 新书
        keyToNode.put(key, node);
        pushFront(node); // 放到最上面
        if (keyToNode.size() > capacity) { // 书太多了
            Node backNode = dummy.prev;
            keyToNode.remove(backNode.key);
            remove(backNode); // 去掉最后一本书
        }
    }

    // 获取 key 对应的节点，同时把该节点移到链表头部
    private Node getNode(int key) {
        if (!keyToNode.containsKey(key)) { // 没有这本书
            return null;
        }
        Node node = keyToNode.get(key); // 有这本书
        remove(node); // 把这本书抽出来
        pushFront(node); // 放到最上面
        return node;
    }

    // 删除一个节点（抽出一本书）
    private void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }

    // 在链表头添加一个节点（把一本书放到最上面）
    private void pushFront(Node x) {
        x.prev = dummy;
        x.next = dummy.next;
        x.prev.next = x;
        x.next.prev = x;
    }
}

// 带TTL的LRU缓存
class TTLLRUCache {
    private static class Node {
        int key, value;
        long expireTime; // 新增TTL
        Node prev, next;

        Node(int k, int v, long expireTime) {
            key = k;
            value = v;
            this.expireTime = expireTime;
        }
    }

    private final int capacity;
    private final Node dummy = new Node(0, 0, 0); // 哨兵节点
    private final Map<Integer, Node> keyToNode = new HashMap<>();

    public TTLLRUCache(int capacity) {
        this.capacity = capacity;
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public int get(int key) {
        Node node = getNode(key);
        if (node == null)
            return -1;

        // 判断是否过期
        if (isExpired(node)) {
            keyToNode.remove(key);
            remove(node);
            return -1;
        }

        return node.value;
    }

    // 新增 ttl 参数
    public void put(int key, int value, long ttlMillis) {
        long expireTime = System.currentTimeMillis() + ttlMillis;

        Node node = getNode(key);
        if (node != null) { // 已存在
            node.value = value;
            node.expireTime = expireTime; // 更新过期时间
            return;
        }

        node = new Node(key, value, expireTime);
        keyToNode.put(key, node);
        pushFront(node);

        if (keyToNode.size() > capacity) {
            Node backNode = dummy.prev;
            keyToNode.remove(backNode.key);
            remove(backNode);
        }
    }

    // 获取节点并移动到头部（保持你的设计）
    private Node getNode(int key) {
        Node node = keyToNode.get(key);
        if (node == null)
            return null;

        remove(node);
        pushFront(node);
        return node;
    }

    private boolean isExpired(Node node) {
        return System.currentTimeMillis() > node.expireTime;
    }

    private void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }

    private void pushFront(Node x) {
        x.prev = dummy;
        x.next = dummy.next;
        x.prev.next = x;
        x.next.prev = x;
    }
}