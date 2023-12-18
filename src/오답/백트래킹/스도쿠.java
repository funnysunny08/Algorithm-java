package 오답.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 2580번: 스도쿠
public class 스도쿠 {

    static int[][] map = new int[9][9];
    static List<int[]> sudoku = new ArrayList<>();

    public static void dfs(int L) {
        if (L == sudoku.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        } else {
            int x = sudoku.get(L)[0];
            int y = sudoku.get(L)[1];
            for (int val = 1; val <= 9; val++) {
                // 해당 행에 val 가 있는지 체크
                boolean isValid = true;
                for (int i = 0; i < 9; i++) {
                    if (map[x][i] == val) {
                        isValid = false;
                        break;
                    }
                }
                if (!isValid) continue;
                // 해당 열에 val 가 있는지 체크
                for (int i = 0; i < 9; i++) {
                    if (map[i][y] == val) {
                        isValid = false;
                        break;
                    }
                }
                if (!isValid) continue;
                // 해당 박스에 val 가 있는지 체크
                // (x / 3) * 3
                for (int i = (x / 3) * 3; i < (x / 3) * 3 + 3; i++) {
                    for (int j = (y / 3) * 3; j < (y / 3) * 3 + 3; j++) {
                        if (map[i][j] == val) {
                            isValid = false;
                            break;
                        }
                    }
                }
                if (!isValid) continue;
                map[x][y] = val;
                dfs(L + 1);
                map[x][y] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) sudoku.add(new int[]{i, j});
            }
        }
        dfs(0);
    }
}
