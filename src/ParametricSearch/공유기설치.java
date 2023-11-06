package ParametricSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 2110번: 공유기 설치
public class 공유기설치 {

    public static int[] house;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        house = new int[N];

        for(int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        // start와 end는 공유기 간의 최소 거리를 의미한다.
        int start = 1;
        int end = house[N - 1] - house[0] + 1;

        while(start < end) { // Upper Bound 형식
            int mid = (end + start) / 2;
            if(canInstall(mid) < M) { // 설치 가능한 공유기 개수가 M 개수보다 작으면 => 거리를 좁힌다!
                end = mid;
            }
            else { // 설치 가능한 공유기 개수가 M개보다 크거나 같으면 => 거리를 벌린다!
                start = mid + 1;
            }
        }
        // Upper Bound는 탐색 값을 초과하는 첫 번쨰 값을 가리킴
        System.out.println(start - 1);
    }

    public static int canInstall(int distance) {

        // 첫 번째 집은 무조건 설치한다고 가정
        int count = 1;
        int lastLocate = house[0];

        for(int i = 1; i < house.length; i++) {
            int locate = house[i];
            if(locate - lastLocate >= distance) {
                count++;
                lastLocate = locate;
            }
        }
        return count;
    }
}
