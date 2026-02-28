import java.util.ArrayList;
import java.util.List;

public class _131 {
    public static void main(String[] args) {

    }

    List<List<String>> res = new ArrayList<>();
    List<String> temp = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0);
        return res;
    }

    private void backtrack(String s, int curr) {
        if (curr == s.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = curr; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (check(sb)) {
                temp.add(sb.toString());
                backtrack(s, i + 1);
                temp.removeLast();
            }
        }
    }

    private boolean check(StringBuilder sb) {
        int left = 0;
        int right = sb.length() - 1;
        while (left < right) {
            if (sb.charAt(left) != sb.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
