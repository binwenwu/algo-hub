

public class _3 {
    public static void main(String[] args) {
        _3 s = new _3();
        s.lengthOfLongestSubstring("pwwkew");
    }

    // 滑动窗口，同时用数组模拟hashmap
    public int lengthOfLongestSubstring(String s) {
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
            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
