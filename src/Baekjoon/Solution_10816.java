package Baekjoon;
// 백준 10816번 - 숫자 카드2 (HashMap 이용)
import java.io.*;
import java.util.*;
public class Solution_10816 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(st.nextToken());
            hm.put(x, hm.getOrDefault(x, 0) + 1);
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < m; i++) {
            int key = Integer.parseInt(st.nextToken());
            if (hm.containsKey(key)) {
                bw.write(hm.get(key) + " ");
            } else {
                bw.write("0 ");
            }
        }
        br.close();
        bw.close();
    }
}
