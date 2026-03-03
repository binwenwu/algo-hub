public class _211 {

}

class WordDictionary {

    private static class Node {
        Node[] son = new Node[26];
        boolean end = false;
    }

    private final Node root = new Node();

    public WordDictionary() {
    }

    public void addWord(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (cur.son[c] == null) { // 无路可走？
                cur.son[c] = new Node(); // new 出来！
            }
            cur = cur.son[c];
        }
        cur.end = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, Node node) {
        if (node == null)
            return false;

        if (index == word.length()) {
            return node.end;
        }

        char c = word.charAt(index);

        if (c == '.') {
            // 枚举所有可能
            for (int i = 0; i < 26; i++) {
                if (node.son[i] != null) {
                    if (dfs(word, index + 1, node.son[i])) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            return dfs(word, index + 1, node.son[c - 'a']);
        }
    }
}