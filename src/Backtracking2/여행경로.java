package Backtracking2;

import java.util.ArrayList;
import java.util.Collections;

// 프로그래머스: 여행 경로
public class 여행경로 {

    static boolean[] visited;
    static ArrayList<String> path;

    public static void dfs(String start, String route, int depth, String[][] tickets) {
        if (depth == tickets.length) {
            path.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && start.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(tickets[i][1], route + " " + tickets[i][1], depth + 1, tickets);
                visited[i] = false;
            }
        }
    }

    public static String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        path = new ArrayList<>();

        dfs("ICN", "ICN", 0, tickets);
        Collections.sort(path);
        return path.get(0).split(" ");
    }

    public static void main(String[] args) {
//        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        String[] answer = solution(tickets);

        for (String a : answer) {
            System.out.println(a);
        }
    }
}
