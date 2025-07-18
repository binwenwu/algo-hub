import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class _110 {
    // 辅助类，用于在队列中存储单词和到达该单词的路径长度
    static class Node {
        String word;
        int length;

        public Node(String word, int length) {
            this.word = word;
            this.length = length;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 读取输入
        int n = sc.nextInt();
        String beginStr = sc.next();
        String endStr = sc.next();

        // 将字典列表放入HashSet中，以便快速查找
        Set<String> wordSet = new HashSet<>();
        wordSet.add(beginStr);
        wordSet.add(endStr);

        for (int i = 0; i < n; i++) {
            wordSet.add(sc.next());
        }
        sc.close();

        System.out.println(findShortestSequence(beginStr, endStr, wordSet));
    }

    public static int findShortestSequence(String beginStr, String endStr, Set<String> wordSet) {
        // 2. 初始化BFS
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new Node(beginStr, 1)); // 路径长度从1开始
        visited.add(beginStr);

        // 3. 执行BFS
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String currentWord = current.word;
            int currentLength = current.length;

            // 4. 检查是否到达终点
            if (currentWord.equals(endStr)) {
                return currentLength;
            }

            // 5. 生成所有可能的邻居
            char[] chars = currentWord.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char originalChar = chars[i]; // 保存原始字符，以便回溯

                // 尝试用 'a' 到 'z' 替换当前位置的字符
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == originalChar) {
                        continue; // 跳过相同的字符
                    }

                    chars[i] = c;
                    String newWord = new String(chars);

                    // 6. 校验邻居并入队
                    // 检查新词是否在字典中，并且没有被访问过
                    if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                        visited.add(newWord);
                        queue.add(new Node(newWord, currentLength + 1));
                    }
                }

                // 恢复原始字符，以便进行下一位置的替换
                chars[i] = originalChar;
            }
        }

        // 7. 如果队列为空仍未找到，说明无解
        return 0;
    }
}
