import java.util.Deque;
import java.util.LinkedList;

public class _71 {
    public static void main(String[] args) {
        _71 s = new _71();
        String path = "/home/";
        System.out.println(s.simplifyPath(path));
    }

    /**
     * 先进行分词，然后依次进行入栈出栈的判断，判断规则：
     * 
     * 普通目录 → 入栈
     * ".." → 出栈
     * "." 或空字符串 → 忽略
     */
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();

        String[] parts = path.split("/");

        for (String part : parts) {

            if (part.equals("") || part.equals(".")) {
                continue;
            }

            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        }

        StringBuilder res = new StringBuilder();

        while (!stack.isEmpty()) {
            res.append("/").append(stack.pop());
        }

        return res.toString();
    }

}
