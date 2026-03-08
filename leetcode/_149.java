import java.util.HashMap;
import java.util.Map;

public class _149 {
    public int maxPoints(int[][] points) {

        int n = points.length;
        if (n <= 2)
            return n;

        int result = 0;

        for (int i = 0; i < n; i++) {

            Map<String, Integer> map = new HashMap<>();
            int duplicates = 0;
            int max = 0;

            for (int j = i + 1; j < n; j++) {

                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicates++;
                    continue;
                }

                int g = gcd(dx, dy);
                dx /= g;
                dy /= g;

                String key = dx + "_" + dy;

                map.put(key, map.getOrDefault(key, 0) + 1);

                max = Math.max(max, map.get(key));
            }

            result = Math.max(result, max + duplicates + 1);
        }

        return result;
    }

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
