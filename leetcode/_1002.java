import java.util.ArrayList;
import java.util.List;

public class _1002 {
    public static void main(String[] args) {

    }

    public List<String> commonChars(String[] words) {
        List<String> res = new ArrayList<>();
        int[] arr = new int[26];
        for (int j = 0; j < words[0].length(); j++) {
            arr[words[0].charAt(j) - 'a']++;
        }

        for (int i = 1; i < words.length; i++) {
            int[] temp = new int[26];
            for (int j = 0; j < words[i].length(); j++) {
                temp[words[i].charAt(j) - 'a']++;
            }

            for (int j = 0; j < 26; j++) {
                arr[j] = Math.min(arr[j], temp[j]);
            }
        }

        for (int i = 0; i < 26; i++) {
            while (arr[i] > 0) {
                res.add(String.valueOf((char) ('a' + i)));
                arr[i]--;
            }
        }
        return res;
    }

}
