import java.util.ArrayDeque;
import java.util.Deque;

public class _394 {
    public static void main(String[] args) {

    }

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int multi = 0;
        Deque<Integer> mul_stack = new ArrayDeque<>();
        Deque<String> s_stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else if (c == '[') {
                mul_stack.push(multi);
                s_stack.push(sb.toString());
                multi = 0;
                sb.setLength(0);
            } else if (c == ']') {
                StringBuilder temp = new StringBuilder();
                int m = mul_stack.pop();
                for (int i = 0; i < m; i++) {
                    temp.append(sb);
                }
                sb = new StringBuilder(s_stack.pop() + temp);
            } else {
                sb.append(c);
            }

        }
        return sb.toString();
    }
}
