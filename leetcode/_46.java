import java.util.ArrayList;
import java.util.List;

public class _46 {
    public static void main(String[] args) {

    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            temp.add(nums[i]);
            used[i] = true;
            backtrack(nums);
            temp.removeLast();
            used[i] = false;
        }
    }
}
