package 기출.카카오;

// 카카오: 파괴되지 않은 건물
public class 파괴되지않은건물_효율성 {

    public static int solution(int[][] board, int[][] skill) {
        int[][] sum = new int[board.length + 1][board[0].length + 1];

        // 누적합 기준값 갱신
        for (int i = 0; i < skill.length; i++) {
            int r1 = skill[i][1], c1 = skill[i][2];
            int r2 = skill[i][3], c2 = skill[i][4];
            int degree = skill[i][5] * (skill[i][0] == 1 ? -1 : 1);
            sum[r1][c1] += degree;
            sum[r2 + 1][c2 + 1] += degree;
            sum[r1][c2 + 1] -= degree;
            sum[r2 + 1][c1] -= degree;
        }

        // 누적합 진행: 상하
        for (int c = 0; c < sum[0].length; c++) {
            for (int r = 1; r < sum.length; r++) {
                sum[r][c] += sum[r - 1][c];
            }
        }
        // 누적합 진행: 좌우
        for (int r = 0; r < sum.length; r++) {
            for (int c = 1; c < sum[0].length; c++) {
                sum[r][c] += sum[r][c - 1];
            }
        }

        //
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += sum[i][j];
                if (board[i][j] > 0) answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
//        int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
//        int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        int[][] board = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};
        System.out.println(solution(board, skill));
    }
}
