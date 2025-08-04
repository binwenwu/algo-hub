public class _925 {
    public static void main(String[] args) {
        _925 s = new _925();
        s.isLongPressedName1("vtkgn", "vttkgnn");
    }


    // 自己想的暴力解法
    public boolean isLongPressedName1(String name, String typed) {
        if (name.length() > typed.length()) {
            return false;
        }
        int slow = 0;
        int fast = 0;
        for (; slow < name.length() && fast < typed.length();) {
            char c1 = name.charAt(slow);
            char c2 = typed.charAt(fast);
            if (c1 != c2) {
                return false;
            }
            if (slow + 1 < name.length() && name.charAt(slow + 1) != c1) {
                while (fast < typed.length() && typed.charAt(fast) == c2) {
                    fast++;
                }
                slow++;
            } else {
                slow++;
                fast++;
            }

        }
        if (slow != name.length()) {
            return false;
        }

        char last = typed.charAt(fast - 1);
        for (int i = fast; i < typed.length(); i++) {
            if (typed.charAt(i) != last) {
                return false;
            }
        }

        return true;
    }

}
