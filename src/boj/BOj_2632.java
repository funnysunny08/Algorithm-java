package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 2632번: 피자판매
public class BOj_2632 {

    static int target;
    static int m, n;
    static int[] A;
    static int[] B;
    static int[] arrA;
    static int[] arrB;
    static int[] sumA;
    static int[] sumB;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        A = new int[m * 2 + 1];
        for (int i = 1; i <= m; i++) {
            A[i] = Integer.parseInt(br.readLine());
            A[i + m] = A[i];
        }
        B = new int[n * 2 + 1];
        for (int i = 1; i <= n; i++) {
            B[i] = Integer.parseInt(br.readLine());
            B[i + n] = B[i];
        }

        // 누적합 구하기
        sumA = new int[m * 2 + 1];
        sumA[1] = A[1];
        for (int i = 2; i <= m * 2; i++) {
            sumA[i] = A[i] + sumA[i - 1];
        }

        sumB = new int[n * 2 + 1];
        sumB[1] = B[1];
        for (int i = 2; i <= n * 2; i++) {
            sumB[i] = B[i] + sumB[i - 1];
        }

        //
        arrA = new int[target + 1];
        arrB = new int[target + 1];
        makeArr(arrA, sumA, m);
        makeArr(arrB, sumB, n);

        //
        int answer = 0;
        // 1. A만 사용
        answer += arrA[target];
        // 2. B만 사용
        answer += arrB[target];
        // 3. A, B 모두 사용
        for (int i = 1; i < target; i++) {
            int a = i;
            int b = target - i;
            answer += arrA[a] * arrB[b];
        }

        System.out.println(answer);
    }

    public static void makeArr(int[] arr, int[] sum, int size) { // arrA(arrB), 누적합 배열, m(n)
        for (int i = 1; i < size; i++) { // 몇개의 조각을 선택할지
            for (int s = 1; s <= size; s++) { // 몇번째 조각부터 연속해서 담을 것인지
                int value = sum[s + i - 1] - sum[s - 1];
                if (value > target) continue; // target보다 큰 경우는 무시
                arr[value]++;
            }
        }
        if (sum[size] <= target) arr[sum[size]]++;
    }
}
