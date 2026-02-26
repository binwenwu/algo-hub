public class _58 {
    // 反向遍历
    public int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int res = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            res++;
            index--;
        }
        return res;
    }
}
