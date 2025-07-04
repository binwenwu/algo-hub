import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _491 {
    public static void main(String[] args) {

    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrack1(0, nums);
        return res;
    }

    private void backtrack1(int start, int[] nums) {
        if (temp.size() > 1) {
            res.add(new ArrayList<>(temp));
        }

        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if ((!temp.isEmpty() && temp.get(temp.size() - 1) > nums[i]) || set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            temp.add(nums[i]);
            backtrack1(i + 1, nums);
            temp.removeLast();
        }
    }

    /**
     * 注意题目中说了，数值范围[-100,100]，所以完全可以用数组来做去重
     */
    private void backtrack2(int start, int[] nums) {
        if (temp.size() > 1) {
            res.add(new ArrayList<>(temp));
        }

        int[] used = new int[201];
        for (int i = start; i < nums.length; i++) {
            if ((!temp.isEmpty() && temp.get(temp.size() - 1) > nums[i]) || used[nums[i] + 100] == 1) {
                continue;
            }
            used[nums[i] + 100] = 1;
            temp.add(nums[i]);
            backtrack2(i + 1, nums);
            temp.removeLast();
        }
    }

}
