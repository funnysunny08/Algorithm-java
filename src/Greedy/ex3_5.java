package Greedy;
import java.util.*;
// 1이 될 때까지
public class ex3_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int result = 0;

        while (n != 1) {
            if (n % k == 0) {
                n /= k;
                result++;
            } else {
                n--;
                result++;
            }
        }
        System.out.println(result);
    }
}
