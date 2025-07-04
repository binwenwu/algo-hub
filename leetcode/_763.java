import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class _763 {
    public static void main(String[] args) {

    }

    // 解法1：自己想的暴力解法
    public List<Integer> partitionLabels1(String s) {
        // result 列表动态存储当前各个分区的长度
        List<Integer> result = new ArrayList<>();
        // map 存储每个字符所属分区的索引（在result列表中的位置）
        Map<Character, Integer> map = new HashMap<>();

        // 我们不直接用你代码里的 temp 变量，直接在循环里处理
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 检查这个字符是否是第一次出现
            if (!map.containsKey(c)) {
                // ---- 情况1：遇到新字符 ----
                // 为这个新字符创建一个新的分区。
                // 新分区的索引就是当前 result 的大小
                map.put(c, result.size());
                // 新分区长度为1
                result.add(1);

            } else {
                // ---- 情况2：遇到已经存在的字符 ----
                // 获取该字符之前所在的分区的索引
                int partitionIndex = map.get(c);

                // 如果该字符所属的分区已经是最后一个分区了，
                // 那么我们只需要简单地将该分区的长度加1即可。
                if (partitionIndex == result.size() - 1) {
                    result.set(partitionIndex, result.get(partitionIndex) + 1);
                } else {
                    // ---- 这就是最核心的合并逻辑 ----
                    // 该字符所属的分区不是最后一个，说明需要合并。
                    // 例如，s="abac", i=2, c='a'。此时 result=[1, 1], map={'a':0, 'b':1}
                    // 'a' 属于分区0，但我们现在已经有了分区1。所以分区0和分区1必须合并。

                    // 1. 计算合并后的新长度
                    int mergedLength = 0;
                    // 从需要合并的起始分区开始，加总所有后续分区的长度
                    for (int j = partitionIndex; j < result.size(); j++) {
                        mergedLength += result.get(j);
                    }
                    mergedLength += 1; // 再加上当前这个字符的长度

                    // 2. 从 result 列表中移除被合并的分区
                    // 从后往前删，防止索引错乱
                    while (result.size() > partitionIndex) {
                        result.remove(result.size() - 1);
                    }

                    // 3. 将合并后的新分区长度添加回去
                    result.add(mergedLength);

                    // 4. 更新 map 中所有被合并的字符的分区索引
                    // 这是此方法效率较低的地方，需要遍历map
                    for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                        if (entry.getValue() > partitionIndex) {
                            entry.setValue(partitionIndex);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 贪心解法
     * 不断更新最远边界，然后一旦i == 最远边界，
     * 说明最远边界之前的字母后续都不会再出现了，则找到一个区间
     * 
     * @param s
     * @return
     */
    public List<Integer> partitionLabels2(String s) {
        List<Integer> result = new LinkedList<>();
        int[] edge = new int[26];
        char[] s_chars = s.toCharArray();
        for (int i = 0; i < s_chars.length; i++) {
            edge[s_chars[i] - 'a'] = i;
        }

        int index = 0;
        int end = 0;
        for (int i = 0; i < s_chars.length; i++) {
            index = Math.max(index, edge[s_chars[i] - 'a']);
            if (index == i) {
                result.add(i - end + 1);
                end = index + 1;
            }
        }
        return result;
    }

}
