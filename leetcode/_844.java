public class _844 {
    public static void main(String[] args) {
        _844 s = new _844();
        s.backspaceCompare1("ab#c", "ad#c");
    }

    // 自己想的双指针
    public boolean backspaceCompare1(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();

        int slow1 = 0;
        int fast1 = 0;
        while (fast1 < ss.length) {
            if (ss[fast1] == '#') {
                slow1 = slow1 == 0 ? 0 : slow1 - 1;
                fast1++;
            } else {
                ss[slow1] = ss[fast1];
                slow1++;
                fast1++;
            }
        }

        int slow2 = 0;
        int fast2 = 0;
        while (fast2 < tt.length) {
            if (tt[fast2] == '#') {
                slow2 = slow2 == 0 ? 0 : slow2 - 1;
                fast2++;
            } else {
                tt[slow2] = tt[fast2];
                slow2++;
                fast2++;
            }
        }

        if (slow1 != slow2) {
            return false;
        }

        for (int i = 0; i < slow1; i++) {
            if (ss[i] != tt[i]) {
                return false;
            }
        }

        return true;
    }

    // 使用 StringBuilder
    public boolean backspaceCompare2(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String str) {
        StringBuffer ret = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            char ch = str.charAt(i);
            if (ch != '#') {
                ret.append(ch);
            } else {
                if (ret.length() > 0) {
                    ret.deleteCharAt(ret.length() - 1);
                }
            }
        }
        return ret.toString();
    }

    // 双指针，不存起来，而是直接比较
    /**
     * 若该字符为退格符，则我们需要多删除一个普通字符，我们让 skip 加 1；
     * 
     * 若该字符为普通字符：
     * 
     * 若 skip 为 0，则说明当前字符不需要删去；
     * 
     * 若 skip 不为 0，则说明当前字符需要删去，我们让 skip 减 1。
     */
    public boolean backspaceCompare3(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;

    }

}
