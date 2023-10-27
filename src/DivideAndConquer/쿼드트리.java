package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 1992번: 쿼드 트리
public class 쿼드트리 {

    static int N;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    static void quadTree(int x, int y, int size) { // map의 각 영역의 대표 값과만 비교해서 테스트!
        if (isPossible(x, y, size)) { // 압축이 가능하다면 해당 값을 append
            sb.append(map[x][y]);
            return;
        }
        // 압축 불가능 => 현재 영역 4등분 해야함! 4등분 해서 탐색
        sb.append('('); // 각 depth에서 여는 괄호로 시작
        int newSize = size / 2; // size 반으로 줄임

        quadTree(x, y, newSize); // 왼쪽 위
        quadTree(x, y + newSize, newSize); // 오른쪽 위
        quadTree(x + newSize, y, newSize); // 왼쪽 아래
        quadTree(x + newSize, y + newSize, newSize); // 오른쪽 아래

        sb.append(')'); // 해당 depth가 끝나면 닫는 괄호
    }

    static boolean isPossible(int x, int y, int size) { // 해당 영역이 압축가능한지 체크하는 함수
        int value = map[x][y]; // 해당 영역의 첫번째 값!
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (value != map[i][j]) return false; // value와 다른 값이 있으면 바로 false return! -> 압축할 수 없기 때문
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
