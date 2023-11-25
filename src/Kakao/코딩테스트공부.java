package Kakao;

import java.util.PriorityQueue;

// 2022 KAKAO TECH INTERNSHIP: 코딩 테스트 공부
public class 코딩테스트공부 {

    public static int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] + o1[1] < o2[0] + o2[1]) {
                return -1;
            } else if (o1[0] + o1[1] > o2[0] + o2[1]) {
                return 1;
            } else {
                return 0;
            }
        });

        for (int[] problem : problems) {
            pq.add(problem);
        }

        while (!pq.isEmpty()) {
            int[] problem = pq.poll();

            if (problem[0] > alp || problem[1] > cop) {
                if (problem[0] > alp) {
                    int original = alp;
                    alp += problem[0] - original;
                    answer += problem[0] - original;
                }
                if (problem[1] > cop) {
                    int original = cop;
                    cop += problem[1] - original;
                    answer += problem[1] - original;
                }
            }

            alp += problem[2];
            cop += problem[3];
            answer += problem[4];
        }

        return answer;
    }

    public static void main(String[] args) {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10,15,2,1,2},{20,20,3,3,4}};
        System.out.println(solution(alp, cop, problems));
    }
}
