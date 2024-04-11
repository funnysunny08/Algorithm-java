package 기출.카카오;

import java.util.ArrayList;
import java.util.List;

// 카카오: 양과 늑대
public class 양과늑대 {

    static int N; // 노드 개수
    static int[] INFO;
    static List<Integer>[] graph;
    static boolean[][][] visited;
    static int max = Integer.MIN_VALUE;

    public static void dfs(int now, int s, int w) {
        if (INFO[now] == 0) s++;
        else if (INFO[now] == 1) w++;

        if (s <= w) return;

        max = Math.max(max, s);

        for (int next : graph[now]) {
            int temp = INFO[now];
            if (!visited[next][s][w]) { // 현재의 이 상황으로 next 를 방문한 적이 있는지..?
                INFO[now] = -1;
                visited[now][s][w] = true;
                dfs(next, s, w);
                INFO[now] = temp;
                visited[now][s][w] = false;
            }
        }
    }

    public static int solution(int[] info, int[][] edges) {
        N = info.length;
        INFO = info;
        graph = new List[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }
        visited = new boolean[N + 1][N + 1][N + 1];

        dfs(0, 0, 0);

        return max;
    }

    public static void main(String[] args) {
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
        System.out.println(solution(info, edges));
    }
}
