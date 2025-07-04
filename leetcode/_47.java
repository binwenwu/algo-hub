import java.util.ArrayList;
import java.util.List;

public class _47 {
    public static void main(String[] args) {

    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums) {
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        int[] used2 = new int[21]; // 这是为了控制每一层不能选择重复值
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || used2[nums[i] + 10] == 1) {
                continue;
            }
            used2[nums[i] + 10] = 1;
            temp.add(nums[i]);
            used[i] = true;
            backtrack(nums);
            temp.removeLast();
            used[i] = false;
        }
    }
}
