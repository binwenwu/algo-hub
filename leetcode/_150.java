import java.util.ArrayDeque;
import java.util.Deque;

public class _150 {
    public static void main(String[] args) {
        _150 s = new _150();
        String[] tokens = { "4","13","5","/","+" };
        System.out.println(s.evalRPN1(tokens));
    }

    public int evalRPN1(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                int x = stack.removeLast();
                int y = stack.removeLast();
                stack.add(calculation(y, x, tokens[i]));
            } else {
                stack.add(Integer.valueOf(tokens[i]));
            }
        }

        return stack.getLast();
    }

    private int calculation(int x, int y, String t) {
        switch (t) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
        }

        return -1;
    }
}
