public class _902 {

    // 贪心
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int len = s.length();
        int k = digits.length;

        int res = 0;

        // 1. 长度小于 len
        for (int i = 1; i < len; i++) {
            res += Math.pow(k, i);
        }

        // 2. 长度等于 len
        for (int i = 0; i < len; i++) {
            boolean hasEqual = false;

            for (String d : digits) {
                char c = d.charAt(0);

                if (c < s.charAt(i)) {
                    res += Math.pow(k, len - i - 1);
                } else if (c == s.charAt(i)) {
                    hasEqual = true;
                    break;
                }
            }

            // 没有相等的，直接结束
            if (!hasEqual) {
                return res;
            }
        }

        // 所有位都匹配成功
        return res + 1;
    }


    // DFS
    public int atMostNGivenDigitSet2(String[] digits, int n) {
        String s = String.valueOf(n);
        int len = s.length();
        int k = digits.length;

        int res = 0;

        // 1. 长度 < len（直接算）
        for (int i = 1; i < len; i++) {
            res += Math.pow(k, i);
        }

        // 2. 长度 = len（DFS）
        return res + dfs(0, true, digits, s);
    }

    private int dfs(int pos, boolean isLimit, String[] digits, String s) {
        // 走完所有位，说明构造了一个合法数
        if (pos == s.length()) {
            return 1;
        }

        int count = 0;

        for (String d : digits) {
            char c = d.charAt(0);

            // 如果有限制，当前位不能超过 N[pos]
            if (isLimit && c > s.charAt(pos)) {
                continue;
            }

            // 下一层是否仍受限制
            boolean nextLimit = isLimit && (c == s.charAt(pos));

            count += dfs(pos + 1, nextLimit, digits, s);
        }

        return count;
    }

}
