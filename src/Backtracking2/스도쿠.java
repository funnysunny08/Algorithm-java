package Backtracking2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 2580번 : 스도쿠
public class 스도쿠 {

    static int[][] map = new int[9][9];
    static ArrayList<Location> blanks = new ArrayList<>(); // 빈칸의 위치 정보

    static class Location {
        int r;
        int c;
        Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static void backtracking(int depth) {
        if (depth == blanks.size()) {
            // 출력
            for (int i = 0; i < 9; i ++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.exit(0);
        }
        Location blank = blanks.get(depth);
        for (int i = 1; i <= 9; i++) {
            // 만약 해당 blank 자리에 i가 들어가면?!
            // 가능한지 check
            // 가능하다면 재귀 호출
            // 불가능하다면 넘어가기!
            if (check(blank, i)) {
                map[blank.r][blank.c] = i;
                backtracking(depth + 1);
                map[blank.r][blank.c] = 0;
            }
        }
    }

    static boolean check(Location blank, int x) {
        // blank의 행에 x가 존재하는지 체크!
        // blank의 열에 x가 존재하는지 체크!
        // blank가 포함된 박스에 x가 존재하는지 체크!
        for (int i = 0; i < 9; i++) {
            if (map[blank.r][i] == x) return false;
            if (map[i][blank.c] == x) return false;
        }

        // 속해 있는 박스 영역 구하기?! -> 3으로 나눴을 때의 몫 => 0 첫번쨰, 1 두번째, 2 세번째
        // 몫 * 3 ~ 몫 * 3 + 2
        int row = blank.r / 3;
        int col = blank.c / 3;
        for (int i = row * 3; i <= row * 3 + 2; i++) {
            for (int j = col * 3; j <= col * 3 + 2; j++) {
                if (map[i][j] == x) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 빈칸의 위치 정보 따로 보관!
                if (map[i][j] == 0) blanks.add(new Location(i, j));
            }
        }
        backtracking(0);
    }
}