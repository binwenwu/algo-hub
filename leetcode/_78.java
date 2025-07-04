import java.util.ArrayList;
import java.util.List;

public class _78 {

    public static void main(String[] args) {

    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(0, nums);
        return res;
    }

    private void backtrack(int start, int[] nums) {
        res.add(new ArrayList<>(temp));
        if (start == nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(i + 1, nums);
            temp.remove(temp.size() - 1);
        }
    }
}
