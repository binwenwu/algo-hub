package nowcoder.alibaba;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/exam/test/95338879/detail?pid=30440590
 * 
 * DFS
 */
public class _7 {

    static int n, m;
    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        path.clear();
        dfs(1);

        sc.close();
    }

    static void dfs(int start) {
        // 选够了
        if (path.size() == m) {
            print();
            return;
        }

        // 剪枝
        for (int i = start; m - path.size() <= n - i + 1; i++) {
            path.add(i);
            dfs(i + 1); // 只能往后选
            path.remove(path.size() - 1);
        }
    }

    static void print() {
        for (int i = 0; i < path.size(); i++) {
            if (i == path.size() - 1) {
                System.out.print(path.get(i));
            } else {
                System.out.print(path.get(i) + " ");
            }
        }
        System.out.println();
    }
}
