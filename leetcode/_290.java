import java.util.HashMap;
import java.util.Map;

/**
 * 双哈希，和205判断同构字符串一样的思路
 */
public class _290 {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length)
            return false;

        Map<Character, String> p2w = new HashMap<>();
        Map<String, Character> w2p = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char p = pattern.charAt(i);
            String w = words[i];

            if ((p2w.containsKey(p) && !p2w.get(p).equals(w)) || (w2p.containsKey(w) && w2p.get(w) != p))
                return false;

            p2w.put(p, w);
            w2p.put(w, p);
        }

        return true;
    }
}
