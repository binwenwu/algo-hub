import java.util.ArrayList;
import java.util.List;

public class _77 {
    public static void main(String[] args) {

    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(1, n, k);
        return res;
    }

    private void backtrack(int start, int n, int k) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        // 这里可以进行剪枝， for(int i = start; n - i + 1 >= k - temp.size();i++)
        for (int i = start; i <= n; i++) {
            temp.add(i); // 做选择
            backtrack(i + 1, n, k); // 递归
            temp.remove(temp.size() - 1); // 回溯，撤销选择
        }
    }

}
