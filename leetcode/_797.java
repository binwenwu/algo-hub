import java.util.ArrayList;
import java.util.List;

public class _797 {
    public static void main(String[] args) {
        _797 s = new _797();
        int[][] graph = { { 1, 2 }, { 3 }, { 3 }, {} };
        List<List<Integer>> allPathsSourceTarget = s.allPathsSourceTarget(graph);
        System.out.println(allPathsSourceTarget);
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    private void dfs(int[][] graph, int x, int n) {
        if (x == n) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < graph[x].length; i++) {
            path.add(graph[x][i]);
            dfs(graph, graph[x][i], n);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        path.add(0);
        dfs(graph, 0, graph.length - 1);
        return result;
    }
}
