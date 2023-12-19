package 오답.분할정복_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1992번: 쿼드 트리
public class 쿼드트리 {

    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void quadTree(int x, int y, int size) { // 탐색 시작 위치 (x, y), 현재 쿼드 트리의 사이즈
        if (isPossible(x, y, size)) { // 압축이 가능하다면 현재값 append!
            sb.append(map[x][y]);
            return;
        }

        // 압축이 불가능하다면 4등분한다!
        sb.append("("); // 각 depth에서 여는 괄호로 시작
        int newSize = size / 2; // size 반으로 줄인다.

        // 쿼드 트리 돌리는 순서 지켜야 함!
        quadTree(x, y, newSize); // 1. 왼쪽 위
        quadTree(x, y + newSize, newSize); // 2. 오른쪽 위
        quadTree(x + newSize, y, newSize); // 3. 왼쪽 아래
        quadTree(x + newSize, y + newSize, newSize); // 4. 오른쪽 아래

        sb.append(")"); // 해당 depth 닫는 괄호로 종료
    }

    public static boolean isPossible(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[x][y] != map[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        quadTree(0, 0, N);
        System.out.println(sb);
    }
}
