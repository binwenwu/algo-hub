import java.util.ArrayDeque;
import java.util.Deque;

public class _1047 {
    public static void main(String[] args) {

    }

    public String removeDuplicates1(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (stack.size() == 0 || stack.getLast() != s.charAt(i)) {
                stack.add(s.charAt(i));
            } else {
                stack.removeLast();
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.removeFirst());
        }

        return sb.toString();
    }

    public String removeDuplicates2(String s) {
        // 将 res 当做栈
        // 也可以用 StringBuilder 来修改字符串，速度更快
        // StringBuilder res = new StringBuilder();
        StringBuffer res = new StringBuffer();
        // top为 res 的长度
        int top = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 当 top >= 0,即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if (top >= 0 && res.charAt(top) == c) {
                res.deleteCharAt(top);
                top--;
                // 否则，将该字符 入栈，同时top++
            } else {
                res.append(c);
                top++;
            }
        }
        return res.toString();
    }
}
