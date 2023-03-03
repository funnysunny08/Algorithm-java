package Greedy;
// 거스름 돈
public class ex3_1 {
    public static void main(String[] args) {
        int n = 1260;
        int cnt = 0;
        int[] coinTypes = {500, 100, 50, 10};

        for (int i = 0; i < coinTypes.length; i++) {
            cnt += n / coinTypes[i];
            cnt %= coinTypes[i];
        }
        System.out.println(cnt);
    }
}
