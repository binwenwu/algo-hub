import java.util.PriorityQueue;

public class _295 {
    public static void main(String[] args) {

    }

}

class MedianFinder {

    PriorityQueue<Integer> A = new PriorityQueue<>();
    PriorityQueue<Integer> B = new PriorityQueue<>((a, b) -> {
        return b - a;
    });

    public MedianFinder() {

    }

    public void addNum(int num) {
        if (A.size() == B.size()) {
            B.offer(num);
            A.offer(B.poll());
        } else {
            A.offer(num);
            B.offer(A.poll());
        }
    }

    public double findMedian() {
        return A.size() == B.size() ? (A.peek() + B.peek()) / 2.0 : A.peek();
    }
}
