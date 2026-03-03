import java.util.ArrayList;
import java.util.List;

/**
 * 先建Trie
 * 再遍历格子
 * 走Trie
 * 走不通就剪枝
 * 找到单词就加入,并置空防止重复加入
 * 走完记得回溯
 */
public class _212 {

    static class TrieNode {
        TrieNode[] son = new TrieNode[26];
        String word; // 不用 boolean，直接存单词
    }

    private List<String> res = new ArrayList<>();
    private char[][] board;
    private int m, n;

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        m = board.length;
        n = board[0].length;

        TrieNode root = buildTrie(words);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, root);
            }
        }

        return res;
    }

    private void dfs(int x, int y, TrieNode node) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }
        char c = board[x][y];
        if (c == '#' || node.son[c - 'a'] == null)
            return;

        node = node.son[c - 'a'];

        // 找到单词
        if (node.word != null) {
            res.add(node.word);
            node.word = null; // 防止重复
        }

        board[x][y] = '#'; // 标记访问
        dfs(x + 1, y, node);
        dfs(x - 1, y, node);
        dfs(x, y + 1, node);
        dfs(x, y - 1, node);
        board[x][y] = c; // 回溯
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                c -= 'a';
                if (cur.son[c] == null) {
                    cur.son[c] = new TrieNode();
                }
                cur = cur.son[c];
            }
            cur.word = word;
        }
        return root;
    }
}
