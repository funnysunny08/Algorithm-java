package Greedy;
import java.util.*;
// 숫자 카드 게임
public class ex3_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 공백을 통해 n, m을 입력 받는다.
        int n = sc.nextInt();
        int m = sc.nextInt();
        int result = 0;

        int x;
        for (int i = 0; i < n; i++) {
            int min_value = 10001;
            for (int j = 0; j < m; j++) {
                x = sc.nextInt();
                min_value = Math.min(min_value, x);
            }
            result = Math.max(result, min_value);
        }
        System.out.println(result);
    }
}
