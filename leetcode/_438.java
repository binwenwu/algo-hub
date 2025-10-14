import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _438 {
    public static void main(String[] args) {
        
    }

    // 定长滑窗
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen)
            return res;

    
        // 记录 p 中每个字符的频率
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; i++) {
            pCount[p.charAt(i) - 'a']++;
        }

        // 滑动窗口统计 s 的子串
        int[] sCount = new int[26];

        for (int i = 0; i < sLen; i++) {
            sCount[s.charAt(i) - 'a']++;

            
            if (i >= pLen) {
                // 左边出去一个字符
                sCount[s.charAt(i - pLen) - 'a']--;
            }

            // 如果窗口大小正好等于 pLen，比较是否是异位词
            if (i >= pLen - 1) {
                if (Arrays.equals(sCount, pCount)) {
                    res.add(i - pLen + 1);
                }
            }
        }

        return res;
    }

    // 定长滑窗
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen)
            return res;

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        for (int i = 0; i < pLen; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(sCount, pCount))
            res.add(0);

        // 滑动窗口: 左边界从 1 到 sLen - pLen
        for (int left = 1; left <= sLen - pLen; left++) {
            // 移出窗口最左边的字符
            sCount[s.charAt(left - 1) - 'a']--;
            // 加入新字符
            sCount[s.charAt(left + pLen - 1) - 'a']++;

            if (Arrays.equals(sCount, pCount)) {
                res.add(left);
            }
        }

        return res;
    }

}
