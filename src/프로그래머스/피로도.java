package 프로그래머스;

// 프로그래머스: 피로도
public class 피로도 {

    static int N;
    static int[] selected;
    static boolean[] visited;
    static int[][] info;
    static int answer = -1;
    static int K;

    public static int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        selected = new int[N];
        visited = new boolean[N];
        info = dungeons;
        K = k;
        dfs(0);
        return answer;
    }

    public static void dfs(int depth) {
        if (depth == N) {
            answer = Math.max(answer, check());
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selected[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    public static int check() {
        int i = 0;
        int p = K;
        for (; i < N; i++) {
            int[] dungeon = info[selected[i]];
            if (p >= dungeon[0]) {
                p -= dungeon[1];
            } else {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[][] dungeons = {{80,20}, {50,40}, {30,10}};
        System.out.println(solution(80, dungeons));
    }
}
