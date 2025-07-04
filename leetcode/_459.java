public class _459 {
    public static void main(String[] args) {
        _459 s = new _459();
        boolean res = s.repeatedSubstringPattern4("abcdeeeeabcd");
        System.out.println(res);
    }

    // 暴力解法
    public boolean repeatedSubstringPattern1(String s) {
        int len = s.length();
        if (len == 1) {
            return false;
        }

        StringBuilder temp = new StringBuilder();

        int i = len;
        while (i >= 2) {
            if (len % i != 0) {
                i--;
            } else {
                int j = len / i;
                String s1 = s.substring(0, j);
                for (int t = 0; t < i; t++) {
                    temp.append(s1);
                }
                if (temp.toString().equals(s)) {
                    return true;
                }
                temp.setLength(0);
                i--;
            }
        }
        return false;
    }

    // 方法1 的优化
    public boolean repeatedSubstringPattern2(String s) {
        int len = s.length();
        if (len == 1)
            return false;

        // i代表重复子串的长度，因为确保最少要重复两次，所以i可以从 len / 2 开始
        for (int i = len / 2; i >= 1; i--) {
            if (len % i == 0) {
                int repeat = len / i;
                String pattern = s.substring(0, i);
                boolean match = true;
                for (int j = 1; j < repeat; j++) {
                    if (!s.substring(j * i, (j + 1) * i).equals(pattern)) {
                        match = false;
                        break;
                    }
                }
                if (match)
                    return true;
            }
        }
        return false;
    }

    // KMP 方法 + 移动匹配
    public boolean repeatedSubstringPattern3(String s) {
        int index = strStr((s + s).substring(1,s.length() * 2 - 1), s);
        return index != -1;
    }

    public int strStr(String haystack, String needle) {
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


    // 纯 KMP 的方法
    public boolean repeatedSubstringPattern4 (String s) {
        if (s.length() == 0) {
            return false;
        }
        int[] next = new int[s.length()];
        getNext(next, s);
        int len = s.length();


        if (next[len - 1] != 0 && len % (len - (next[len - 1] )) == 0) {
            return true;
        }
        return false;
    }
}
