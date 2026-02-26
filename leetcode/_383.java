public class _383 {
    public static void main(String[] args) {

    }

    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];

        // 统计 magazine
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }

        // 消耗 ransomNote
        for (char c : ransomNote.toCharArray()) {
            count[c - 'a']--;
            if (count[c - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}
