import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _373 {
    /**
     * 优先队列：
     * 
     * 比如： nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     * 
     * 他们的组合是一个二维递增矩阵
     * (1,2) (1,4) (1,6)
     * (7,2) (7,4) (7,6)
     * (11,2) (11，4) (11,6)
     * 
     * 
     * 先把第一列加进优先队列，然后取出最小的一个，然后把那一行向左平移
     * 其实也就是把取出那个组合的右边组合加入队列，然后重复刚刚的操作，
     * 这样就能保证每次取出的是第一列中最小的
     * 
     * 当然，在第一次加入队列的时候，要满足： i < Math.min(nums1.length, k)，因为nums1中k往后的，加进去也选不上
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> res = new ArrayList<>();

        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));

        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            pq.offer(new int[] { i, 0 });
        }

        while (k-- > 0 && !pq.isEmpty()) {

            int[] cur = pq.poll();
            int i = cur[0];
            int j = cur[1];

            res.add(Arrays.asList(nums1[i], nums2[j]));

            if (j + 1 < nums2.length) {
                pq.offer(new int[] { i, j + 1 });
            }
        }

        return res;
    }
}
