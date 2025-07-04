import java.util.ArrayList;
import java.util.List;

public class _131 {
    public static void main(String[] args) {

    }

    // 自己写的回溯，没有考虑任何优化
    List<List<String>> res = new ArrayList<>();
    List<String> temp = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack2(0, s);
        return res;
    }

    private void backtrack(int start, String s) {
        if (start == s.length() && temp.size() > 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            // 可以考虑用StringBuilder，而不是每次都 new 一个 String，参考 backtrack2
            String curr = s.substring(start, i + 1);
            if (check(curr)) {
                temp.add(curr);
                backtrack(i + 1, s);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean check(String sb) {
        for (int i = 0; i < sb.length() / 2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private void backtrack2(int start, String s) {
        if (start == s.length() && temp.size() > 0) {
            res.add(new ArrayList<>(temp));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            // 可以考虑用StringBuilder，而不是每次都 new 一个 String
            sb.append(s.charAt(i));
            if (check2(sb)) {
                temp.add(sb.toString());
                backtrack2(i + 1, s);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean check2(StringBuilder sb) {
        for (int i = 0; i < sb.length() / 2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

}
