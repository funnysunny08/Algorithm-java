package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 25542. 약속 장소
public class BOJ_25542 {
    static int N, L;
    static String[] arr;
    static List<Character>[] alphas;

    private static void dfs(int idx, String str) {
        if (idx == L) {
            System.out.println(str);
            System.exit(0);
            return;
        }
        for (char ch : alphas[idx]) {
            if (check(str + ch, idx)) dfs(idx + 1, str + ch);
        }
    }

    private static boolean check(String str, int idx) {
        for (int i = 0; i < N; i++) {
            if (getDiff(str, arr[i], idx) > 1) return false;
        }
        return true;
    }

    private static int getDiff(String str1, String str2, int idx) {
        int diff = 0;
        for (int i = 0; i <= idx; i++) {
            if (str1.charAt(i) != str2.charAt(i)) diff++;
            if (diff >= 2) return diff;
        }
        return diff;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new String[N];
        alphas = new List[L];
        for (int i = 0; i < L; i++) alphas[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            for (int j = 0; j < L; j++) {
                if (!alphas[j].contains(arr[i].charAt(j))) alphas[j].add(arr[i].charAt(j));
            }
        }
        dfs(0, "");
        System.out.println("CALL FRIEND");
    }
}
