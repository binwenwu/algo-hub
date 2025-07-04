import java.util.LinkedList;
import java.util.Queue;

public class _225 {
    public static void main(String[] args) {
        MyStack1 stack1 = new MyStack1();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        System.out.println(stack1.top());
    }

}

// 用两个队列实现
class MyStack1 {

    Queue<Integer> queue1;
    Queue<Integer> queue2;


    public MyStack1() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    public void push(int x) {
        if (!queue1.isEmpty()) {
            queue1.add(x);
        } else {
            queue2.add(x);
        }
    }
    
    public int pop() {
        if (!queue1.isEmpty()) {
            int size = queue1.size();
            for (int i = 0; i < size - 1; i++) {
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        } else {
            int size = queue2.size();
            for (int i = 0; i < size - 1; i++) {
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }
    }
    
    public int top() {
        if (!queue1.isEmpty()) {
            int size = queue1.size();
            for (int i = 0; i < size - 1; i++) {
                queue2.add(queue1.poll());
            }
            int temp = queue1.poll();
            queue2.add(temp);
            return temp;
        } else {
            int size = queue2.size();
            for (int i = 0; i < size - 1; i++) {
                queue1.add(queue2.poll());
            }
            int temp = queue2.poll();
            queue1.add(temp);
            return temp;
        }
    }
    
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

// 用一个队列实现
class MyStack2{
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack2() {
        queue = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        int n = queue.size();
        queue.offer(x);
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
