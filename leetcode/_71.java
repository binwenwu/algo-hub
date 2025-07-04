import java.util.ArrayDeque;
import java.util.Deque;

public class _71 {
    public static void main(String[] args) {
        _71 s = new _71();
        String path = "/home/";
        System.out.println(s.simplifyPath1(path));
    }

    public String simplifyPath1(String path) {
        Deque<String> deque = new ArrayDeque<>();
        String[] paths = path.split("/");

        for (String dir : paths) {
            if (dir.equals("") || dir.equals(".")) {
                // 忽略空字符串和当前目录
                continue;
            } else if (dir.equals("..")) {
                if (!deque.isEmpty()) {
                    deque.removeLast();
                }
            } else {
                deque.addLast(dir); // 加入有效路径段
            }
        }

        // 构建结果路径
        if (deque.isEmpty())
            return "/";

        StringBuilder sb = new StringBuilder();
        for (String dir : deque) {
            sb.append("/").append(dir);
        }

        return sb.toString();
    }

    // 用数组模拟栈
    public String simplifyPath2(String path) {
        String[] dirs = path.split("/");
        int index = 0;
        for (String dir : dirs) {
            if (dir.equals(".") || dir.isEmpty()) {
                continue;
            }
            if (dir.equals("..")) {
                index = Math.max(0, index - 1);
            } else {
                dirs[index++] = dir;
            }
        }
        if (index == 0) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < index; i++) {
            sb.append("/").append(dirs[i]);
        }
        return sb.toString();
    }
}
