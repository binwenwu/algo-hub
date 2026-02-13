import java.util.ArrayList;
import java.util.List;

public class _17 {
    public static void main(String[] args) {

    }

    List<String> res = new ArrayList<>();
    String[] map = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    StringBuilder sb = new StringBuilder();

    public List<String> letterCombinations(String digits) {
        backtrack(digits, 0);
        return res;
    }

    private void backtrack(String digits, int curr) {
        if (curr == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String temp = map[digits.charAt(curr) - '0'];
        for (int i = 0; i < temp.length(); i++) {
            sb.append(temp.charAt(i));
            backtrack(digits, curr + 1);
            sb.setLength(sb.length() - 1);
        }
    }

}
