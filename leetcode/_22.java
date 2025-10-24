import java.util.ArrayList;
import java.util.List;

public class _22 {
    public static void main(String[] args) {

    }

    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    

    public List<String> generateParenthesis(int n) {
        backtracking(n, 0, 0);
        return res;
    }

    public void backtracking(int n, int open, int close) {
        // 当左右括号都用完时，收集结果
        if (path.length() == n * 2) {
            res.add(path.toString());
            return;
        }

        // 只要左括号还没用完，就可以加左括号
        if (open < n) {
            path.append('(');
            backtracking(n, open + 1, close);
            path.deleteCharAt(path.length() - 1); // 回溯
        }

        // 右括号数量必须小于左括号数量时，才能加右括号
        if (close < open) {
            path.append(')');
            backtracking(n, open, close + 1);
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }
}
