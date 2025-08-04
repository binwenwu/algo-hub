import java.util.HashMap;
import java.util.Map;

public class _205 {
    public static void main(String[] args) {

    }

    // 使用哈希表
    public boolean isIsomorphic1(String s, String t) {
        Map<Character, int[]> maps = new HashMap<>();
        Map<Character, int[]> mapt = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ts = t.charAt(i);
            int[] arr_s = maps.getOrDefault(cs, new int[2]);
            int[] arr_t = mapt.getOrDefault(ts, new int[2]);
            if (arr_s[0] != arr_t[0] || arr_s[1] != arr_t[1]) {
                return false;
            }
            arr_s[0]++;
            arr_s[1] = i;
            maps.put(cs, arr_s);
            arr_t[0]++;
            arr_t[1] = i;
            mapt.put(ts, arr_t);
        }
        return true;
    }

    // 其实不用这么麻烦，直接记录映射关系即可
    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> maps = new HashMap<Character, Character>();
        Map<Character, Character> mapt = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((maps.containsKey(x) && maps.get(x) != y) || (mapt.containsKey(y) && mapt.get(y) != x)) {
                return false;
            }
            maps.put(x, y);
            mapt.put(y, x);
        }
        return true;
    }

}
