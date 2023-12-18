package 오답.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1025번: 제곱수 찾기
public class 제곱수찾기 {

    /*
    * 1. 해당 규칙을 만족하는 모든 정수를 구한다.
    * 2. 정수를 구할 때마다 완전 제곱수인지 체크하고, 완전 제곱수라면 answer 를 업데이트한다.
    * */

    static int N, M;
    static int[][] arr;
    static int answer = Integer.MIN_VALUE;

    public static void checkPerfectSquareNumber(int number) {
        if (number < 0) return;
        int sqrt = (int) Math.sqrt(number);
        if (sqrt * sqrt == number) {
            answer = Math.max(number, answer);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        for (int dx = -N; dx < N; dx++) { // 행의 등차
            for (int dy = -M; dy < M; dy++) { // 열의 등차
                for (int sx = 0; sx < N; sx++) { // 행의 시작 index
                    for (int sy = 0; sy < M; sy++) { // 열의 시작 index

                        if (dx == 0 && dy == 0) continue;
                        int idxX = sx;
                        int idxY = sy;
                        StringBuilder sb = new StringBuilder();
                        while (idxX >= 0 && idxY >= 0 && idxX < N && idxY < M) {
                            sb.append(arr[idxX][idxY]);
                            idxX += dx;
                            idxY += dy;
                            // 완전제곱수 체크를 여기서 해야 함!!!! 모든 숫자를 탐색해야 하니깐,,,
                            checkPerfectSquareNumber(Integer.parseInt(sb.toString()));
                        }
                    }
                }
            }
        }

        if (answer == Integer.MIN_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
