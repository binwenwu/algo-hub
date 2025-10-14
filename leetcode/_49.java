import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _49 {
    public static void main(String[] args) {

    }

    // 暴力解法
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, Integer> count = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            count.put(strs[i], count.getOrDefault(strs[i], 0) + 1);
        }

        for (int i = 0; i < strs.length; i++) {
            if (count.get(strs[i]) == 0) {
                continue;
            }
            List<String> list = new ArrayList<>();
            list.add(strs[i]);
            count.put(strs[i], count.get(strs[i]) - 1);
            for (int j = i + 1; j < strs.length; j++) {
                if (isAnagrams(strs[i], strs[j])) {
                    list.add(strs[j]);
                    count.put(strs[j], count.get(strs[j]) - 1);
                }
            }
            res.add(list);
        }

        return res;
    }

    private boolean isAnagrams(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 由于互为字母异位词的两个字符串包含的字母相同，
     * 因此对两个字符串分别进行排序之后得到的字符串一定是相同的，故可以将排序之后的字符串作为哈希表的键。
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<List<String>>(map.values());
    }

}
