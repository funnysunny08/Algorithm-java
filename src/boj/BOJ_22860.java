package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 백준 22860번: 폴더 정리 (small)
public class BOJ_22860 {

    static int N, M;
    static HashMap<String, List<String>> folders = new HashMap<>();
    static HashMap<String, List<String>> files = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String child = st.nextToken();
            int chk = Integer.parseInt(st.nextToken()); // 1 -> 폴더, 0 -> 파일
            if (chk == 0) { // file
                if (!files.containsKey(parent)) files.put(parent, new ArrayList<>());
                files.get(parent).add(child);
            } else { // folder
                if (!folders.containsKey(parent)) folders.put(parent, new ArrayList<>());
                folders.get(parent).add(child);
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            String[] query = br.readLine().split("/");
            String q = query[query.length - 1];
            List<String> list = new ArrayList<>();

            Queue<String> folderQ = new LinkedList<>();
            folderQ.offer(q);
            while (!folderQ.isEmpty()) {
                String now = folderQ.poll();
                if (files.containsKey(now))
                    list.addAll(files.get(now));

                if (folders.containsKey(now)) {
                    for (String f : folders.get(now)) folderQ.offer(f);
                }
            }
            Set<String> set = new HashSet<>(list);
            sb.append(set.size() + " " + list.size() + "\n");
        }
        System.out.println(sb);
    }
}
