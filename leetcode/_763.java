import java.util.LinkedList;
import java.util.List;

public class _763 {
    public static void main(String[] args) {

    }

    /**
     * 贪心解法
     * 不断更新最远边界，然后一旦 i == 最远边界，
     * 说明最远边界之前的字母后续都不会再出现了，则找到一个区间
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new LinkedList<>();
        int[] edge = new int[26];
        char[] s_chars = s.toCharArray();
        for (int i = 0; i < s_chars.length; i++) {
            edge[s_chars[i] - 'a'] = i;
        }

        int left = 0;
        int right = 0;
        for (int i = 0; i < s_chars.length; i++) {
            right = Math.max(right, edge[s_chars[i] - 'a']);
            if (right == i) {
                result.add(right - left + 1);
                left = right + 1;
            }
        }
        return result;
    }

}
