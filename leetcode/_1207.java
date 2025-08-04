import java.util.HashMap;
import java.util.Map;

public class _1207 {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 2, 1, 1, 3, 2 };
        _1207 s = new _1207();
        s.uniqueOccurrences(arr);
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        boolean[] flag = new boolean[arr.length + 1];

        for (int i : map.values()) {
            if (!flag[i]) {
                flag[i] = true;
            } else {
                return false;
            }
        }

        return true;
    }
}
