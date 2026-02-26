import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _392 {
    // 双指针
    public boolean isSubsequence1(String s, String t) {
        int s_index = 0;
        int t_index = 0;
        while (s_index < s.length() && t_index < t.length()) {
            if (s.charAt(s_index) == t.charAt(t_index)) {
                s_index++;
                t_index++;
            } else {
                t_index++;
            }
        }

        return s_index == s.length();
    }

    /**
     * 如果有很多个 s 要判断，但 t 是固定的，怎么优化？
     * 把 t 变成索引表，后续查询走二分
     */
    Map<Character, List<Integer>> map = new HashMap<>();

    public boolean isSubsequence2(String s, String t) {
        /**
         * 构建 t 的索引表
         */
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            List<Integer> list = map.getOrDefault(c, new ArrayList<>());
            list.add(i);
            map.put(c, list);
        }

        int prev = -1;

        for (char c : s.toCharArray()) {
            if (!map.containsKey(c))
                return false;

            List<Integer> list = map.get(c);
            int idx = nextGreater(list, prev);
            if (idx == -1)
                return false;

            prev = idx;
        }

        return true;
    }

    // 二分查找第一个 > prev 的位置
    private int nextGreater(List<Integer> list, int prev) {
        int l = 0, r = list.size() - 1;
        int ans = -1;

        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (list.get(mid) > prev) {
                ans = list.get(mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
