import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _332 {
    public static void main(String[] args) {


        List<List<String>> tickets = new ArrayList<>(List.of(
                new ArrayList<>(List.of("JFK", "ZZZ")),
                new ArrayList<>(List.of("JFK", "AAA")),
                new ArrayList<>(List.of("ZZZ", "JFK"))));

        Collections.sort(tickets, (a, b) -> b.get(1).compareTo(a.get(1)));
        System.out.println(tickets);

        // _332 s = new _332();
        // s.findItinerary1(tickets);
        // System.out.println(s.res);
    }

    // 这种回溯的方法超时
    List<String> res;
    List<String> temp = new ArrayList<>();
    boolean[] used;

    public List<String> findItinerary1(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
        used = new boolean[tickets.size()];
        temp.add("JFK");
        backtrack("JFK", tickets);
        return res;
    }

    private boolean backtrack(String start, List<List<String>> tickets) {
        if (temp.size() == tickets.size() + 1) {
            res = new ArrayList<>(temp);
            return true;
        }

        for (int i = 0; i < tickets.size(); i++) {
            if (used[i]) {
                continue;
            }
            List<String> curr = tickets.get(i);
            if (curr.get(0).equals(start)) {
                String end = curr.get(1);
                used[i] = true;
                temp.add(end);
                if (backtrack(end, tickets)) {
                    return true;
                }
                temp.removeLast();
                used[i] = false;
            }
        }

        return false;
    }

    // 所以最好还是按图的方式来解答
    private Map<String, List<String>> g = new HashMap<>();
    private List<String> ans = new ArrayList<>();

    public List<String> findItinerary2(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> b.get(1).compareTo(a.get(1)));
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            if (!g.containsKey(from)) {
                g.put(from, new ArrayList<>());
            }
            g.get(from).add(to);
        }

        dfs("JFK");
        Collections.reverse(ans);
        return ans;
    }

    private void dfs(String f) {
        while (g.containsKey(f) && !g.get(f).isEmpty()) {
            String t = g.get(f).remove(g.get(f).size() - 1);
            dfs(t);
        }
        ans.add(f);
    }
}
