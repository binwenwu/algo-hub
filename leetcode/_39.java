import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _39 {
    public static void main(String[] args) {
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // 先进行排序
        backtrack(0, target, candidates);
        return res;
    }

    private void backtrack(int start, int target, int[] candidates) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            temp.add(candidates[i]);
            sum += candidates[i];
            backtrack(i, target, candidates);
            temp.remove(temp.size() - 1);
            sum -= candidates[i];
        }
    }

    // 可以做一下剪枝，首先是对candidates进行排序
    private void backtrack2(int start, int target, int[] candidates) {

        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            temp.add(candidates[i]);
            sum += candidates[i];
            backtrack(i, target, candidates);
            temp.remove(temp.size() - 1);
            sum -= candidates[i];
        }
    }
}
