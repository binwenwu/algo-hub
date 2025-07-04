import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _90 {
    public static void main(String[] args) {

    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(0, nums);
        return res;
    }

    private void backtrack(int start, int[] nums) {
        res.add(new ArrayList<>(temp));
        if (start == nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            backtrack(i + 1, nums);
            temp.removeLast();
        }
    }
}
