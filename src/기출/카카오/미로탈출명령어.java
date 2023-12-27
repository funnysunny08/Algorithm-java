package 기출.카카오;

// 프로그래머스: 미로 탈출 명령어
public class 미로탈출명령어 {

    static int[] dx = {1, 0, 0, -1}; // d, l, r, u (하, 좌, 우, 상)
    static int[] dy = {0, -1, 1, 0};
    static String[] dc = {"d", "l", "r", "u"};
    static int N, M, R, C, K;
    static String answer;
    static StringBuilder sb = new StringBuilder();

    public static void dfs(int L, int x, int y) {
        if (answer != null) return; // 답으로 설정된 애가 있으면 모든 재귀 종료!
        if (L + distance(x, y) > K) return; // 현재 위치에서의 목표까지의 거리 + 남은 이동 > K => 더 탐색해봤자 불가능!
        if (L == K) {
            if (x == R && y == C) {
                answer = sb.toString();
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            sb.append(dc[i]);
            dfs(L + 1, nx, ny);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static int distance(int x, int y){
        return (int) Math.abs(x - R) + (int) Math.abs(y - C);
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        // n x m의 미로
        // 출발지점 (x, y)
        // 탈출구 (r, c)
        // 이동 거리 k
        N = n;
        M = m;
        R = r - 1;
        C = c - 1;
        K = k;

        int length = distance(x - 1, y - 1);
        if((k - length) % 2 == 1 || k < length) return "impossible";

        dfs(0, x - 1, y - 1);
        if (answer == null) return "impossible";
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(3,4,2,3,3,1,5));
//        System.out.println(solution(2, 3, 1, 1, 2, 2, 2));
//        System.out.println(solution(3,3,1,2,3,3,4));
    }
}
