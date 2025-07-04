import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _40 {
    public static void main(String[] args) {

    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    int sum = 0;
    int pre = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // 先进行排序
        backtrack(0, target, candidates);
        return res;
    }

    private void backtrack(int start, int target, int[] candidates) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] == pre) { // 这种方式去重不太好，参考backtrack2的去重
                continue;
            }
            if (sum + candidates[i] > target) {
                break;
            }
            temp.add(candidates[i]);
            sum += candidates[i];
            backtrack(i + 1, target, candidates);
            temp.remove(temp.size() - 1);
            sum -= candidates[i];
            pre = candidates[i];
        }
    }

    private void backtrack2(int start, int target, int[] candidates) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (sum + candidates[i] > target) {
                break;
            }
            temp.add(candidates[i]);
            sum += candidates[i];
            backtrack(i + 1, target, candidates);
            temp.remove(temp.size() - 1);
            sum -= candidates[i];
            pre = candidates[i];
        }
    }
}
