public class _5 {
    public static void main(String[] args) {

    }

    // 中心扩散法
    public String longestPalindrome(String s) {
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = 1;
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                len++;
                left--;
            }
            while (right < s.length() && s.charAt(right) == s.charAt(i)) {
                len++;
                right++;
            }
            while (right < s.length() && left >= 0 && s.charAt(left) == s.charAt(right)) {
                len = len + 2;
                right++;
                left--;
            }
            if (len > maxLen) {
                maxLen = len;
                start = left + 1;
            }
        }

        return s.substring(start, start + maxLen);

    }

}
