package 오답.kakao;

// 카카오: 파괴되지 않은 건물
public class 파괴되지않은건물 {

    public static int solution(int[][] board, int[][] skill) {
        int[][] sum = new int[board.length + 1][board[0].length + 1];

        for (int i = 0; i < skill.length; i++) {
            int r1 = skill[i][1], c1 = skill[i][2];
            int r2 = skill[i][3], c2 = skill[i][4];
            int degree = skill[i][5] * (skill[i][0] == 1 ? -1 : 1);

            sum[r1][c1] += degree;
            sum[r2 + 1][c2 + 1] += degree;
            sum[r1][c2 + 1] -= degree;
            sum[r2 + 1][c1] -= degree;
        }

        // 좌우 누적합
        for (int r = 0; r < sum.length; r++) {
            for (int c = 1; c < sum[0].length; c++) {
                sum[r][c] += sum[r][c - 1];
            }
        }

        // 상하 누적합
        for (int c = 0; c < sum[0].length; c++) {
            for (int r = 1; r < sum.length; r++) {
                sum[r][c] += sum[r - 1][c];
            }
        }

        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + sum[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {

    }
}
