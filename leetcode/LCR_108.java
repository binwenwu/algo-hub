import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LCR_108 {
    public static void main(String[] args) {

    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // 从两端开始的搜索集合
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        // 已访问的单词
        Set<String> visited = new HashSet<>();

        int level = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // 优化：总是从较小的集合开始搜索
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            // 下一层的单词集合
            Set<String> nextLevel = new HashSet<>();

            for (String word : beginSet) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char originalChar = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar)
                            continue;

                        chars[i] = c;
                        String newWord = new String(chars);

                        // 如果在另一端的搜索集合中找到，说明相遇了
                        if (endSet.contains(newWord)) {
                            return level + 1;
                        }

                        // 如果在字典中且未访问过，加入下一层
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            nextLevel.add(newWord);
                            visited.add(newWord);
                        }
                    }

                    chars[i] = originalChar;
                }
            }

            beginSet = nextLevel;
            level++;
        }

        return 0;
    }

}
