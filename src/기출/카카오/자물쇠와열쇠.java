package 기출.카카오;

// 카카오: 자물쇠와 열쇠
public class 자물쇠와열쇠 {

    static int N, M; // 자물쇠 N, 열쇠 M
    static int[][] KEY;
    static int[][] LOCK;
    static int holes; // 자물쇠에 있는 구멍 수


    public static boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        KEY = key;
        LOCK = lock;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lock[i][j] == 0) holes++;
            }
        }

        for (int r = 0; r < 4; r++) {
            for (int i = 0; i < N + M; i++) {
                for (int j = 0; j < N + M; j++) {
                    if (check(i, j)) return true;
                }
            }
            rotate();
        }

        return false;
    }

    public static void rotate() {
        int[][] temp = new int[M][M];
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++) temp[j][M-1-i] = KEY[i][j];
        }
        KEY = temp;
    }

    public static boolean check(int x, int y) {
        int sx = x - M + 1;
        if (sx < 0) sx = 0;
        int ex = x;
        if (ex >= N) ex = N - 1;
        int sy = y - M + 1;
        if (sy < 0) sy = 0;
        int ey = y;
        if (ey >= N) ey = N - 1;
        int dx = M - 1 - x, dy = M - 1 - y;

        int cnt = 0;
        for (int i = sx; i <= ex; i++) {
            for (int j = sy; j <= ey; j++) {
                if (LOCK[i][j] == 1) { // 홀 X
                    if (KEY[i + dx][j + dy] == 1) return false;
                } else { // 홀
                    if (KEY[i + dx][j + dy] == 1) {
                        cnt++;
                    } else return false;
                }
            }
        }
        return cnt == holes;
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(solution(key, lock));
    }
}
