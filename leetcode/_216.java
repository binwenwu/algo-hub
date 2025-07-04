import java.util.ArrayList;
import java.util.List;

public class _216 {
    public static void main(String[] args) {

    }


    // 回溯
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    int temp_sum = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(1, n, k);
        return res;
    }

    private void backtrack(int start, int n, int k) {

        if (temp.size() == k) {
            if (temp_sum == n) {
                res.add(new ArrayList<>(temp));
                return;
            }
        }
        if (temp_sum >= n) {
            return;
        }

        for (int i = start; 9 - i + 1 >= k - temp.size(); i++) {
            temp.add(i);
            temp_sum += i;
            backtrack(i + 1, n, k);
            temp.remove(temp.size() - 1);
            temp_sum -= i;
        }
    }


     // 回溯算法的模版
    //  void backtracking(参数) {
    //     if (终止条件) {
    //         存放结果;
    //         return;
    //     }

    //     for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
    //         处理节点;
    //         backtracking(路径，选择列表); // 递归
    //         回溯，撤销处理结果
    //     }
    // }

}
