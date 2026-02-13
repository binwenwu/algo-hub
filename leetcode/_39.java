import java.util.ArrayList;
import java.util.List;

public class _39 {

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtrack(candidates, target, 0);
        return ans;
    }

    public void backtrack(int[] candidates, int target, int start) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            sum = sum + candidates[i];
            temp.add(candidates[i]);
            backtrack(candidates, target, i);
            sum = sum - candidates[i];
            temp.remove(temp.size() - 1);
        }
    }
}
