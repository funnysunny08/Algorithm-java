package BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 1025번: 제곱수 찾기
public class 제곱수찾기 {

    static int N;
    static int M;
    static int[][] map;

    static boolean checkSquareNumber(int num) {
        if (Math.sqrt(num) % 1 == 0) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int answer = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int di = -N; di < N; di++) {
                    for (int dj = -M; dj < M; dj++) {
                        if (di == 0 && dj == 0) {
                            continue;
                        }

                        // 초기값, 초기위치
                        int num = 0;
                        int newRow = i;
                        int newCol = j;

                        while (newRow >= 0 && newRow < N && newCol >= 0 && newCol < M) {
                            num = num * 10 + map[newRow][newCol];

                            if (checkSquareNumber(num)) {
                                answer = Math.max(answer, num);
                            }

                            newRow += di;
                            newCol += dj;
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

}
