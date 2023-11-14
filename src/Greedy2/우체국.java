package Greedy2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 백준 2141번: 우체국
public class 우체국 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Town> towns = new ArrayList<>();
        long sum = 0;
        for (long i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long location = Integer.parseInt(st.nextToken());
            long number = Integer.parseInt(st.nextToken());
            towns.add(new Town(location, number));
            sum += number;
        }
        Collections.sort(towns);
        //
        long result = 0;
        for (int i = 0; i < N; i++) {
            result += towns.get(i).number;
            if (result >= (sum + 1) / 2) {
                System.out.println(towns.get(i).location);
                break;
            }
        }
    }

    static class Town implements Comparable<Town> {
        long location;
        long number;
        public Town(long location, long number) {
            this.location = location;
            this.number = number;
        }

        @Override
        public int compareTo(Town o) {
            return (int) (this.location - o.location);
        }
    }
}
