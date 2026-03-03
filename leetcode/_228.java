import java.util.ArrayList;
import java.util.List;

public class _228 {

    /**
     * 我们从数组的位置 0 出发，向右遍历
     * 每次遇到相邻元素之间的差值大于 1 时，我们就找到了一个区间
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int left = 0;
        int n = nums.length;
        while (left < n) {
            int right = left + 1;
            while (right < n && nums[right] == nums[right - 1] + 1) {
                right++;
            }
            right--;

            if (left < right) {
                ret.add(nums[left] + "->" + nums[right]);
            } else {
                ret.add(Integer.toString(nums[left]));
            }

            left = right + 1;
        }
        return ret;
    }

}
