import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class _127 {

    /**
     * 最短路径用BFS
     * 队列按层扩展
     * 每位替换26字母
     * 命中过字典就入队并记录已访问过
     * 层数就是答案
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord))
            return 0;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(beginWord);
        visited.add(beginWord);

        int step = 0;

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord))
                    return step;

                char[] arr = word.toCharArray();

                // 枚举每一位
                for (int j = 0; j < arr.length; j++) {
                    char old = arr[j];

                    // 替换 26 字母
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old)
                            continue;

                        arr[j] = c;
                        String next = new String(arr);

                        // 在字典里 且 没访问过
                        if (dict.contains(next) && !visited.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }

                    arr[j] = old; // 回溯
                }
            }

        }

        return 0;
    }
}
