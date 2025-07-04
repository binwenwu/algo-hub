public class _28 {
    public static void main(String[] args) {

    }

    public int strStr1(String haystack, String needle) {
        String n1 = needle.substring(0, 1);

        for (int i = 0; i < haystack.length() - (needle.length() - 1); i++) {
            if ((haystack.charAt(i) + "").equals(n1)) {
                if (haystack.substring(i, i + needle.length()).equals(needle)) {
                    return i;
                }
            }
        }

        return -1;
    }

    // 方法1的优化，不要用字符串进行比较，避免重复创建 substring 对象
    public int strStr2(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m)
                return i;
        }
        return -1;
    }

    // KMP 算法
    // 前缀表（不减一）
    public int strStr3(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        int[] next = new int[needle.length()];
        getNext(next, needle);

        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && needle.charAt(j) != haystack.charAt(i))
                j = next[j - 1];
            if (needle.charAt(j) == haystack.charAt(i))
                j++;
            if (j == needle.length())
                return i - needle.length() + 1;
        }
        return -1;

    }

    private void getNext(int[] next, String s) {
        int j = 0;
        next[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = next[j - 1];
            if (s.charAt(j) == s.charAt(i))
                j++;
            next[i] = j;
        }
    }
}
