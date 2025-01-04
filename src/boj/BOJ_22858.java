package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 22858. 원상 복구 (small)
public class BOJ_22858 {
    static int N, K;
    static int[] d;
    static List<Card> cards = new ArrayList<>();

    private static void reverse() {
        // i에 위치한 카드를 d[i]로 이동시킨다.
        for (int i = 0; i < N; i++) {
            cards.get(i).order = d[cards.get(i).order];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cards.add(new Card(Integer.parseInt(st.nextToken()), i));
        }

        st = new StringTokenizer(br.readLine());
        d = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            d[i] = Integer.parseInt(st.nextToken()); // d[i] 에 위치한 카드를 i로 이동시킨다.
        }

        for (int i = 0; i < K; i++) reverse();
        cards.sort((o1, o2) -> o1.order - o2.order);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(cards.get(i).number + " ");
        System.out.println(sb);
    }

    static class Card {
        int number, order;

        public Card(int number, int order) {
            this.number = number;
            this.order = order;
        }
    }
}
