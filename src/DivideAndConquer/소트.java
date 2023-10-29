package DivideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 1083번: 소트
public class 소트 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        int S = Integer.parseInt(br.readLine());

        int start = 0;
        while (S > 0 && start < N) {
            // S 거리 내에 가장 큰 값 찾기
            int max = 0;
            int idx = 0; // 가장 큰 값의 index
            for (int i = start; i < N && i <= S + start; i++) {
                if (max < arr.get(i)) {
                    max = arr.get(i);
                    idx = i;
                }
            }

            if (max <= arr.get(start)) { // 굳이 바꿀 필요 없는 상황
                start++;
                continue;
            }

            // 바꾸기 -> 바꾸고자 하는 target을 start 자리로 오게 하고 나머지는 한칸씩 뒤로 밀기
            arr.add(start, max); // 원래 있던 자리에 max 값 넣기
            arr.remove(idx + 1); // 뒤에 있던 max는 삭제

            S -= (idx - start); // 얼마나 이동했는지
            start++;
        }

        for (int i = 0; i < N; i++) {
            System.out.print(arr.get(i) + " ");
        }
    }
}
