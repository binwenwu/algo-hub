import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class _20 {
    public static void main(String[] args) {

    }

    public boolean isValid1(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int len = s.length();
        if (len % 2 != 0) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i);
            if (c1 == '(' || c1 == '{' || c1 == '[') {
                stack.add(c1);
            } else {
                if (stack.size() == 0) {
                    return false;
                } else {
                    char c2 = stack.removeLast();
                    if (getRight(c1) != c2) {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }

    private char getRight(char left) {
        switch (left) {
            case '(':
                return ')';
            case '{':
                return '}';
            case '[':
                return ']';
        }

        return ' ';
    }
}
