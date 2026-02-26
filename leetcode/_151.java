
public class _151 {
    public static void main(String[] args) {
        _151 s = new _151();
        String res = s.reverseWords(" t sdad 1");
        System.out.println(res);
    }
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {

            // 1. 跳过空格
            while (i >= 0 && s.charAt(i) == ' ')
                i--;

            if (i < 0)
                break;

            int j = i;

            // 2. 找单词左边界
            while (j >= 0 && s.charAt(j) != ' ')
                j--;

            // 3. 加入单词
            sb.append(s.substring(j + 1, i + 1)).append(" ");

            i = j - 1; // 继续往前
        }

        return sb.toString().trim();
    }
}
