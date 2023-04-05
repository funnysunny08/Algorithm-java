package Programmers;
// 프로그래머스 - 땅따먹기
import java.util.*;
public class Solution_014 {
    public static int solution(int[][] land) {
        int answer = 0;
        int curr = -1;
        for (int i = 0; i < land.length; i++) {
            int maxAt = 0;
            for (int j = 0; j < land[i].length; j++) if (land[i][j] > land[i][maxAt]) maxAt = j;
            if (curr == maxAt) {
                land[i][maxAt] = -99999;
                maxAt = 0;
                for (int j = 0; j < land[i].length; j++)
                    maxAt = land[i][j] > land[i][maxAt] ? j : maxAt;
            }
            curr = maxAt;
            answer += land[i][maxAt];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] land = {{1,2,3,5},{5,6,7,8}, {4,3,2,1}};
        int ans = solution(land);
        System.out.println(ans);
    }
}
