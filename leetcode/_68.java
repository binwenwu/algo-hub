import java.util.ArrayList;
import java.util.List;

public class _68 {

    /**
     * 模拟
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int j = i;
            int len = 0;

            // 1. 贪心装一行
            while (j < words.length && len + words[j].length() + (j - i) <= maxWidth) {
                len += words[j].length();
                j++;
            }

            int gaps = j - i - 1; // 单词间隔数
            StringBuilder line = new StringBuilder();

            // 2. 判断是不是最后一行 or 单词只有1个
            if (j == words.length || gaps == 0) {
                // 左对齐
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k != j - 1)
                        line.append(" ");
                }
                // 补尾部空格
                int need = maxWidth - line.length();
                for (int n = 0; n < need; n++) {
                    line.append(" ");
                }
            } else {
                // 3. 普通行：均匀分配空格
                int totalSpaces = maxWidth - len;
                int avg = totalSpaces / gaps; // 每个间隙最少空格
                int extra = totalSpaces % gaps; // 左边多出来的空格

                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        int need = avg;
                        if (extra > 0) {
                            need++;
                            extra--;
                        }

                        for (int n = 0; n < need; n++) {
                            line.append(" ");
                        }
                    }
                }
            }

            res.add(line.toString());
            i = j;
        }

        return res;
    }
}
