import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _399 {

    /**
     * 本质是带权图路径乘积问题
     * 
     * 核心建模： 把变量当成节点：
     * 
     * a --2.0--> b --3.0--> c
     * 
     * 同时建反向边：
     * 
     * b --0.5--> a
     * c --1/3--> b
     * 
     * 问题变成： 求两个点之间路径的权重乘积
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        // 1. 建图
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];

            if (!graph.containsKey(a)) {
                graph.put(a, new HashMap<>());
            }

            if (!graph.containsKey(b)) {
                graph.put(b, new HashMap<>());
            }

            graph.get(a).put(b, val);
            graph.get(b).put(a, 1.0 / val);
        }

        // 2. 处理查询
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                res[i] = -1.0;
            } else if (start.equals(end)) {
                res[i] = 1.0;
            } else {
                res[i] = dfs(graph, start, end, new HashSet<>(), 1.0);
            }
        }

        return res;
    }

    private double dfs(Map<String, Map<String, Double>> graph,
            String cur, String target,
            Set<String> visited,
            double product) {

        if (cur.equals(target))
            return product;

        visited.add(cur);

        for (Map.Entry<String, Double> entry : graph.get(cur).entrySet()) {
            if (visited.contains(entry.getKey()))
                continue;

            double res = dfs(graph, entry.getKey(), target, visited,
                    product * entry.getValue());
            if (res != -1.0)
                return res;
        }

        return -1.0;
    }
}
