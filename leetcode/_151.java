
public class _151 {
    public static void main(String[] args) {
        _151 s = new _151();
        String res = s.reverseWords("a good   example");
        System.out.println(res);
    }

    // 双指针
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

    // 分词后倒序遍历拼接
    public String reverseWords2(String s) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (!strs[i].equals("")) {
                sb.append(strs[i]).append(" ");
            }

        }

        return sb.toString().trim();
    }
}
