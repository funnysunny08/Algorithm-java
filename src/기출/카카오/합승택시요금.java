package 기출.카카오;

import java.util.Arrays;

// 카카오: 합승 택시 요금
public class 합승택시요금 {

    int[][] dist;
    int MAX = 1000001;
    int fee;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }

        for (int i = 0; i < fares.length; i++) {
            dist[fares[i][0]][fares[i][1]] = fares[i][2];
            dist[fares[i][1]][fares[i][0]] = fares[i][2];
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        fee = dist[s][a] + dist[s][b];

        for (int i = 1; i <= n; i++) {
            fee = Math.min(fee, dist[s][i] + dist[i][a] + dist[i][b]);
        }

        return fee;
    }
}
