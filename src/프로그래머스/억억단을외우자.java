package 프로그래머스;

import java.util.Arrays;
import java.util.Comparator;

// 프로그래머스: 억억단을 외우자
public class 억억단을외우자 {

    static Point[] dp;

    static class Point {
        int num, c;

        public Point(int num, int c) {
            this.num = num;
            this.c = c;
        }
    }

    public static int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        dp = new Point[e + 1];
        for (int i = 0; i <= e; i++) dp[i] = new Point(i, 0);

        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                dp[j].c++;
            }
        }

        Arrays.sort(dp, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.c != o2.c) return o2.c - o1.c;
                else return o1.num - o2.num;
            }
        });

        for (int i = 0; i < starts.length; i++) {
            for (int j = 0; j <= e; j++) {
                if (dp[j].num >= starts[i]) {
                    answer[i] = dp[j].num;
                    break;
                }
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        int[] starts = {1,3,7};
        int[] ans = solution(8, starts);
        for (int a : ans) {
            System.out.println(a);
        }
    }
}
