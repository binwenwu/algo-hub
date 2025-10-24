import java.util.PriorityQueue;
import java.util.Queue;

public class _4 {
    public static void main(String[] args) {

    }

    // 模仿 leetcode 295 题的做法
    Queue<Integer> A = new PriorityQueue<>();
    Queue<Integer> B = new PriorityQueue<>((x, y) -> (y - x));

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        for (int i : nums1) {
            addNum(i);
        }
        for (int i : nums2) {
            addNum(i);
        }

        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }

    public void addNum(int num) {
        if (A.size() == B.size()) {
            B.add(num);
            A.add(B.poll());
        } else {
            A.add(num);
            B.add(A.poll());
        }
    }

    // 

}
