public class _14 {
    /**
     * 纵向扫描
     * 使用第一个字符串作为基准，然后开始后面的每个字符串进行扫描判断
     * 外循环是基准字符串的第i个字符，内循环是1～末尾字符串的第i个字符
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String base = strs[0];
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return base.substring(0, i);
                }
            }
        }

        return base;
    }
}
