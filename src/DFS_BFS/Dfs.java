package DFS_BFS;
import java.util.*;
// DFS - 재귀 호출
public class Dfs {
    static final int MAX_N = 10;
    static int N, E; // 노드 개수, 간선 개수
    static int[][] Graph = new int[MAX_N][MAX_N];
    static boolean[] Visited = new boolean[MAX_N]; // 방문 여부 표현

    static void dfs (int node) {
        Visited[node] = true;
        System.out.println(node + " ");

        for (int next = 0; next < N; ++next) {
            if (!Visited[next] && Graph[node][next] != 0)
                dfs(next);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        E = sc.nextInt();

        for (int i = 0; i < E; ++i) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            Graph[u][v] = Graph[v][u] = 1;
        }
        // 입력 예시
        // 5 6
        // 0 1 0 2 1 3 1 4 2 4 3 4
        dfs(0);
    }
}
