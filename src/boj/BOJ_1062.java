package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 백준 1062번: 가르침
public class BOJ_1062 {

    static int K;
    static boolean[] visited = new boolean[26];
    static List<String> strList = new ArrayList<>();
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            str.replace("anta", "");
            str.replace("tica", "");
            strList.add(str);
        }

        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(n);
            return;
        }

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        backtracking(0, 0);
        System.out.println(answer);
    }

    public static void backtracking(int depth, int s) {
        if (depth == K - 5) {
            int cnt = 0;
            for (String str : strList) {
                boolean chk = true;
                for (int i = 0; i < str.length(); i++) {
                    if (!visited[str.charAt(i) - 'a']) {
                        chk = false;
                        break;
                    }
                }
                if (chk) cnt++;
            }
            answer = Math.max(answer, cnt);
            return;
        }
        for (int i = s; i < 26; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            backtracking(depth + 1, i + 1);
            visited[i] = false;
        }
    }
}
