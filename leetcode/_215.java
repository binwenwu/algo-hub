import java.util.PriorityQueue;

public class _215 {
    public static void main(String[] args) {

    }

    // 优先队列
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((pair1, pair2) -> pair2 - pair1);
        for (int num : nums) {
            pq.offer(num);
        }
        while (k > 1) {
            pq.poll();
            k--;
        }
        return pq.poll();
    }

    // 基于快速排序的选择方法
    public int findKthLargest2(int[] _nums, int k) {
        int n = _nums.length;
        // 第 k 大也就是升序排序后下标为 n - k 的元素
        return quickselect(_nums, 0, n - 1, n - k);
    }

    public int quickselect(int[] nums, int l, int r, int k) {
        // 只剩一个数了，直接返回
        if (l == r) {
            return nums[k];
        }
        // 找一个基准值
        int x = nums[l], i = l - 1, j = r + 1;
        while (true) {
            i++;
            while (nums[i] < x) {
                i++;
            }
            j--;
            while (nums[j] > x) {
                j--;
            }
            if (i >= j) {
                break;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        if (k <= j) {
            return quickselect(nums, l, j, k);
        } else {
            // 因为这里 k 指的是元素的下标，不是指第几小，所以左右侧都是传入k
            return quickselect(nums, j + 1, r, k);
        }

    }

    // 基于堆排序的选择方法

}
