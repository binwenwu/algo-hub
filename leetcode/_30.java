import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _30 {
    /**
     * 分组滑窗 + 哈希表
     * 
     * 为什么要“分组滑窗”？
     * 假设： ["foo","bar"]， wordLen = 3 表示每个单词的长度
     * 字符串：barfoothefoobarman
     * 我们要按 3 个字符一组去看：
     * bar | foo | the | foo | bar | man
     * 但问题来了：
     * 如果从 index = 1 开始呢？
     * arf | oot | hef | oob | arm ...
     * 也是可能的！
     * 所以起点可能是：0，1，2
     * 也就是： i in [0, wordLen-1]
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || words.length == 0)
            return res;

        int wordLen = words[0].length();
        int wordCount = words.length;

        Map<String, Integer> target = new HashMap<>();
        for (String w : words) {
            target.put(w, target.getOrDefault(w, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) { // 在各分组下做滑动窗口
            /**
             * 每个分组滑动轮次中：
             * window：当前窗口单词频率
             * target：目标单词频率
             * count：窗口里单词数量
             */
            int left = i, right = i;
            Map<String, Integer> window = new HashMap<>();
            int count = 0;

            while (right + wordLen <= s.length()) {
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                // 不在目标中，重置窗口
                if (!target.containsKey(word)) {
                    window.clear();
                    count = 0;
                    left = right;
                    continue;
                }

                // 在目标中，加入窗口
                window.put(word, window.getOrDefault(word, 0) + 1);
                count++;

                // 若单词超频，从 left 开始按 wordLen 长度来收缩窗口
                while (window.get(word) > target.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);
                    window.put(leftWord, window.get(leftWord) - 1);
                    count--;
                    left += wordLen;
                }

                // 如果窗口刚好装满, 匹配成功
                if (count == wordCount) {
                    res.add(left);
                }
            }
        }

        return res;
    }
}
