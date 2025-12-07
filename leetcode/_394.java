import java.util.LinkedList;

public class _394 {
    public static void main(String[] args) {

                        
    }

    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else if (c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res.setLength(0);
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i = 0; i < cur_multi; i++) {
                    tmp.append(res);
                }
                // "2[abc]3[cd]ef"，像这种情况第一次的时候，stack_res弹出的是""字符串，所以不会报 NoSuchElementException
                res = new StringBuilder(stack_res.removeLast() + tmp);
            } else {
                res.append(c);
            }

        }
        return res.toString();
    }
}
