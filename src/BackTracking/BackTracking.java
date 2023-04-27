package BackTracking;
// 백트래킹 기본 코드
public class BackTracking {
    int m;
    int n;
    boolean[] visited = new boolean[n];
    void backtracking(int cnt, int idx) {
        if (cnt == m) {
            // 조건 만족시 해야될 일 적용
            return;
        }
        for (int i = idx; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true; // 상태변화
                backtracking(cnt + 1, i + 1);
                visited[i] = false; // 원상복구
            }
        }
    }
}
