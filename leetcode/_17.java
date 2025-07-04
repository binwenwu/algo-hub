import java.util.ArrayList;
import java.util.List;

public class _17 {
    public static void main(String[] args) {

    }

    // 自己的写的回溯
    List<String> res = new ArrayList<>();
    int length;
    String[] letterMap = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz", // 9
    };
    StringBuilder temp = new StringBuilder();

    public List<String> letterCombinations(String digits) {

        length = digits.length();
        if (length == 0) {
            return res;
        }

        backtrack(0, digits);
        return res;
    }

    private void backtrack(int start, String digits) {
        if (start == length) {
            res.add(temp.toString());
            return;
        }

        String curr = letterMap[digits.charAt(start) - '0'];
        for (int j = 0; j < curr.length(); j++) {
            temp.append(curr.charAt(j));
            backtrack(start + 1, digits);
            temp.setLength(temp.length() - 1); // 删除最后一个字符
        }

    }

    private void backtrack2(int start, String digits) {
        if (temp.length() == length) {
            res.add(temp.toString());
            return;
        }

        for (int i = start; i < length; i++) {
            String curr = letterMap[digits.charAt(i) - '0'];
            for (int j = 0; j < curr.length(); j++) {
                temp.append(curr.charAt(j));
                backtrack(i + 1, digits);
                temp.setLength(temp.length() - 1); // 删除最后一个字符
            }
        }
    }

    // backtrack 和 backtrack2 都行，第一个更好，才是正统的回溯思想，
    // 一层只处理一个 digit 的所有可能字母。

}
