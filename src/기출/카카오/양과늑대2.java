package 기출.카카오;

import java.util.ArrayList;
import java.util.List;

// 카카오: 양과 늑대
public class 양과늑대2 {

    static int[] infos;
    static List<Integer>[] graph;
    static boolean[][][] visited;
    static int maxS;

    public static int solution(int[] info, int[][] edges) {
        infos = info;
        graph = new List[info.length];
        for (int i = 0; i < info.length; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }

        visited = new boolean[17][18][18]; // (node 번호, 양의 수, 늑대의 수)

        dfs(0, 0, 0);
        return maxS;
    }

    public static void dfs(int now, int s, int w) {
        if (visited[now][s][w]) return;
        visited[now][s][w] = true;

        int tmp = infos[now];
        int ps = s, pw = w;
        if (infos[now] == 0) s++;
        else if (infos[now] == 1) w++;

        infos[now] = -1;

        if (w < s) {
            maxS = Math.max(maxS, s);
            for (int next: graph[now]) {
                dfs(next, s, w);
            }
        }
        // 원상복구
        infos[now] = tmp;
        visited[now][ps][pw] = false;
    }

    public static void main(String[] args) {
        int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
        int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
        System.out.println(solution(info, edges));
    }
}
