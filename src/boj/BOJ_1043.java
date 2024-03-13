package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 백준 1043번: 거짓말
public class BOJ_1043 {

    static int N, M;
    static boolean[] truth;
    static Set<Integer> people = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        truth = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        for (int i = 0; i < p; i++) {
            int x = Integer.parseInt(st.nextToken());
            truth[x] = true;
            people.add(x);
        }

        List<String> partyList = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            partyList.add(str);
            String[] arr = str.split(" ");
            int n = Integer.parseInt(arr[0]);

            boolean flag = false;
            for (int j = 1; j <= n; j++) {
                if (people.contains(Integer.parseInt(arr[j]))) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                for (int j = 1; j <= n; j++) {
                    int x = Integer.parseInt(arr[j]);
                    truth[x] = true;
                    people.add(x);
                }
            }
        }
        int cnt = 0;
        L:
        for (String party : partyList) {
            String[] arr = party.split(" ");
            for (int i = 1; i < arr.length; i++) {
                if (people.contains(Integer.parseInt(arr[i]))) continue L;
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}
