import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class _76 {
  public static void main(String[] args) {
    _76 solution = new _76();
    String s = "A";
    String t = "A";
    String res = solution.minWindow2(s, t);
    System.out.println("res: " + res);
  }

  // 自己想的解法，想参考 904 采摘水果的思路，滑动窗口
  public String minWindow1(String s, String t) {
    char[] s_chars = s.toCharArray();
    char[] t_chars = t.toCharArray();
    Map<Character, Integer> t_count = new HashMap<>();
    Map<Character, Integer> s_count = new HashMap<>();
    int left = 0;
    int right = 0;
    int[] minLen = new int[] { 0, s_chars.length - 1 };
    boolean find = false;

    for (char t_char : t_chars) {
      t_count.put(t_char, t_count.getOrDefault(t_char, 0) + 1);
    }

    while (right < s_chars.length) {
      s_count.put(s_chars[right], s_count.getOrDefault(s_chars[right], 0) + 1);
      while (isSatisfy1(s_count, t_count) == 1) {
        if (right - left <= minLen[1] - minLen[0]) {
          find = true;
          minLen[0] = left;
          minLen[1] = right;
        }
        s_count.put(s_chars[left], s_count.get(s_chars[left]) - 1);
        if (s_count.get(s_chars[left]) == 0) {
          s_count.remove(s_chars[left]);
        }
        left++;
      }
      right++;
    }

    if (!find) {
      return "";
    } else {
      return s.substring(minLen[0], minLen[1] + 1);
    }
  }

  public int isSatisfy1(Map<Character, Integer> s_count,
      Map<Character, Integer> t_count) {
    Set<Character> keySet = t_count.keySet();
    for (char k : keySet) {
      if (s_count.getOrDefault(k, 0) < t_count.get(k)) {
        return -1;
      }
    }
    return 1;
  }

  // 同样的思路，但是把Map换成数组，因为char本身有 ascii 码，用 ascii 码来当数组索引
  public String minWindow2(String s, String t) {
    char[] s_chars = s.toCharArray();
    char[] t_chars = t.toCharArray();
    int[] t_count = new int[128];
    int[] s_count = new int[128];
    int left = 0;
    int right = 0;
    int[] minLen = new int[] { 0, s.length() - 1 };
    boolean find = false;

    for (char t_char : t_chars) {
      t_count[t_char]++;
    }

    while (right < s.length()) {
      s_count[s_chars[right]]++;
      while (isSatisfy2(s_count, t_count)) {
        if (right - left <= minLen[1] - minLen[0]) {
          find = true;
          minLen[0] = left;
          minLen[1] = right;
        }
        s_count[s_chars[left]]--;
        left++;
      }
      right++;
    }

    if (!find) {
      return "";
    } else {
      return s.substring(minLen[0], minLen[1] + 1);
    }
  }

  public boolean isSatisfy2(int[] s_chars, int[] t_chars) {
    for (int i = 'A'; i <= 'Z'; i++) {
      if (s_chars[i] < t_chars[i]) {
        return false;
      }
    }
    for (int i = 'a'; i <= 'z'; i++) {
      if (s_chars[i] < t_chars[i]) {
        return false;
      }
    }
    return true;
  }
}
