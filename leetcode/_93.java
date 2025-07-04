import java.util.ArrayList;
import java.util.List;

public class _93 {
    public static void main(String[] args) {

    }

    // 自己写的回溯
    List<String> res = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) {
            return res;
        }
        backtrack(0, s, 0);
        return res;
    }

    private void backtrack(int start, String s, int has) {

        if (start == s.length()) {
            if (has == 4) {
                for (int i = 0; i < 3; i++) {
                    sb.append(temp.get(i)).append(".");
                }
                sb.append(temp.get(3));
                res.add(sb.toString());
                sb.setLength(0);
                return;
            } else {
                return;
            }
        }

        if (s.charAt(start) == '0') {
            temp.add("0");
            backtrack(start + 1, s, has + 1);
            temp.remove(temp.size() - 1);
        } else {
            for (int i = start; i < start + 3 && i < s.length(); i++) {
                String curr = s.substring(start, i + 1);
                if (Integer.valueOf(curr) <= 255) {
                    temp.add(curr);
                    backtrack(i + 1, s, has + 1);
                    temp.remove(temp.size() - 1);
                } else {
                    break;
                }
            }
        }
    }

}
