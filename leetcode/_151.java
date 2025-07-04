import java.util.ArrayList;
import java.util.List;

public class _151 {
    public static void main(String[] args) {
        _151 s = new _151();
        s.reverseWords2(" t sdad 1");
    }

    // 自己想的暴力解法
    public String reverseWords1(String s) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) == ' ') {
                        list.add(s.substring(i, j));
                        i = j;
                        break;
                    }

                    if (j == s.length() - 1) {
                        list.add(s.substring(i, j + 1));
                        i = j;
                        break;
                    }
                }
            }
        }

        String res = "";

        for (int i = 0; i < list.size(); i++) {
            res = " " + list.get(i) + res;
        }

        res = res.substring(1, res.length());
        return res;
    }

    // 使用StringBuilder更快，因为String是不可变类型，每次改变都会新创建一个对象
    public String reverseWords2(String s) {
        StringBuilder res = new StringBuilder();

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {

                for (int j = i; j >= 0; j--) {
                    if (s.charAt(j) == ' ') {
                        res.append(s.substring(j + 1, i + 1)).append(" ");
                        i = j;
                        break;
                    }
                    if (j == 0) {
                        res.append(s.substring(j, i + 1)).append(" ");
                        i = j;
                        break;
                    }
                }

            }
        }

        return res.toString().substring(0, res.length() - 1);
    }
}
