import java.util.ArrayList;
import java.util.List;

public class _93 {
    public static void main(String[] args) {

    }

    // 回溯
    List<String> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) {
            return res;
        }
        backtrack(0, s);
        return res;
    }

    private void backtrack(int start, String s) {

        if (start == s.length()) {
            if (temp.size() == 4) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    sb.append(temp.get(i));
                    if (i != 3) {
                        sb.append('.');
                    }
                }
                res.add(sb.toString());
            }
            return;
        }

        if (s.charAt(start) == '0') {
            temp.add(0);
            backtrack(start + 1, s);
            temp.remove(temp.size() - 1);
        } else {
            int curr = 0;
            for (int i = start; i < s.length(); i++) {
                curr = curr * 10 + (s.charAt(i) - '0');
                if (curr <= 255) {
                    temp.add(curr);
                    backtrack(i + 1, s);
                    temp.remove(temp.size() - 1);
                } else {
                    break;
                }
            }
        }
    }

}
