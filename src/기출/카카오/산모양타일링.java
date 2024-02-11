package 기출.카카오;

// 카카오: 산 모양 타일링
public class 산모양타일링 {

    /**
     * 아래 방향 삼각형을 덮는 방법
     * 1. 위쪽 삼각형과 함께 마름모로 덮는다.
     * 2. 왼쪽 삼각형과 함께 마름모로 덮는다.
     * 3. 오른쪽 삼각형과 함께 마름모로 덮는다.
     * 4. 정삼각형으로 덮는다.
     */

    public static int solution(int n, int[] tops) {
        int[] a = new int[n + 1]; // a[k]: k번째 아래 방향 삼각형을 덮는 방법이 3번인 경우
        int[] b = new int[n + 1]; // b[k]: k번째 아래 방향 삼각형을 덮는 방법이 3번이 아닌 경우

        a[1] = 1;
        if (tops[0] == 1) {
            b[1] = 3;
        } else {
            b[1] = 2;
        }

        for (int i = 2; i <= n; i++) {
            a[i] = (a[i - 1] + b[i - 1]) % 10007;
            if (tops[i - 1] == 1) {
                b[i] = (a[i - 1] * 2 + b[i - 1] * 3) % 10007;
            } else {
                b[i] = (a[i - 1] + b[i - 1] * 2) % 10007;
            }
        }
        return (a[n] + b[n]) % 10007;
    }

    public static void main(String[] args) {
        int[] tops = {1, 1, 0, 1};
        System.out.println(solution(4, tops));
    }
}
