package 기출.카카오;

// 카카오: 파괴되지 않은 건물
public class 파괴되지않은건물 {

    public static int solution(int[][] board, int[][] skill) {
        for (int i = 0; i < skill.length; i++) {
            // r1: skill[i][1], c1: skill[i][2], r2: skill[i][3], c2: skill[i][4]
            // degree: skill[i][5]
            for (int r = skill[i][1]; r <= skill[i][3]; r++) {
                for (int c = skill[i][2]; c <= skill[i][4]; c++) {
                    if (skill[i][0] == 1) {
                        board[r][c] -= skill[i][5];
                    } else {
                        board[r][c] += skill[i][5];
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
//        int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
//        int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        int[][] board = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};
        System.out.println(solution(board, skill));
    }
}
