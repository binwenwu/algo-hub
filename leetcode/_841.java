import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _841 {
    // DFS
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];

        dfs(0, rooms, visited);

        // 检查是否全部访问
        for (boolean v : visited) {
            if (!v)
                return false;
        }

        return true;
    }

    private void dfs(int room, List<List<Integer>> rooms, boolean[] visited) {
        visited[room] = true;

        for (int key : rooms.get(room)) {
            if (!visited[key]) {
                dfs(key, rooms, visited);
            }
        }
    }

    // BFS
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int room = queue.poll();

            for (int key : rooms.get(room)) {
                if (!visited[key]) {
                    visited[key] = true;
                    queue.offer(key);
                }
            }
        }

        for (boolean v : visited) {
            if (!v)
                return false;
        }

        return true;
    }
}
