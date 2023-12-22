package 오답.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 백준 2141번: 우체국
public class 우체국 {

    static class Info implements Comparable<Info> {
        long location, people;
        Info (long location, long people) {
            this.location = location;
            this.people = people;
        }
        @Override
        public int compareTo(Info o) {
            return (int) (this.location - o.location);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        List<Info> infos = new ArrayList<>();
        long total = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long location = Integer.parseInt(st.nextToken());
            long people = Integer.parseInt(st.nextToken());
            infos.add(new Info(location, people));
            total += people;
        }

        Collections.sort(infos);

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += infos.get(i).people;
            if (sum >= (total + 1) / 2) {
                System.out.println(infos.get(i).location);
                break;
            }
        }
    }
}
