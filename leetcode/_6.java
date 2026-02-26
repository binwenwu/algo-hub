import java.util.ArrayList;
import java.util.List;

public class _6 {

    /**
     * 算法流程：
     * 按顺序遍历字符串 s ：
     * 
     * res[i] += c： 把每个字符 c 填入对应行 si；
     * i += flag： 更新当前字符 c 对应的行索引；
     * flag = - flag： 在达到 Z 字形转折点时，执行反向。
     * 
     * 链接：https://leetcode.cn/problems/zigzag-conversion/solutions/21610/zzi-xing-bian-huan-by-jyd/
     */
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows)
            return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int row = 0;
        int step = 1; // 方向：1 向下，-1 向上

        for (char c : s.toCharArray()) {
            rows.get(row).append(c);

            if (row == 0)
                step = 1;
            else if (row == numRows - 1)
                step = -1;

            row += step;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : rows) {
            res.append(sb);
        }

        return res.toString();
    }
}
