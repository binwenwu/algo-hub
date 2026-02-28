import java.util.ArrayList;
import java.util.List;

public class _228 {

    /**
     * 我们从数组的位置 0 出发，向右遍历
     * 每次遇到相邻元素之间的差值大于 1 时，我们就找到了一个区间
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<String>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int high = i - 1;

            if (low < high) {
                ret.add(nums[low] + "->" + nums[high]);
            } else {
                ret.add(Integer.toString(nums[low]));
            }
        }
        return ret;
    }

}
