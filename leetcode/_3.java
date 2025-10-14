import java.util.HashMap;
import java.util.Map;

public class _3 {
    public static void main(String[] args) {
        _3 s = new _3();
        s.lengthOfLongestSubstring1("pwwkew");
    }

    // 自己的解法，map配合双指针
    public int lengthOfLongestSubstring1(String s) {
        int len = s.length();
        if (len <= 1) {
            return len;
        }

        int res = 1;
        int left = 0;
        int right = 1;
        char[] charArray = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        map.put(charArray[0], 0);

        while (right < len) {
            if (map.get(charArray[right]) == null) {
                map.put(charArray[right], right);
                right++;
            } else {
                int index = map.get(charArray[right]);
                if (index >= left) {
                    left = index + 1;
                }
                map.put(charArray[right], right);
                right++;
            }

            res = Math.max(res, right - left);
        }

        return res;
    }

    // 滑动窗口，同时用数组模拟hashmap
    public int lengthOfLongestSubstring2(String s) {
        int len = s.length();
        char[] charArray = s.toCharArray();
        boolean[] has = new boolean[128]; // 本题中字符均为 ASCII 字符，所以 ∣Σ∣≤128

        int res = 0;
        int left = 0;
        int right;
        for (right = 0; right < len; right++) {
            while (has[charArray[right]]) {
                has[charArray[left]] = false;
                left++;
            }
            
            has[charArray[right]] = true;
            res = Math.max(res, right - left  + 1);
        }


        return res;
    }
}
