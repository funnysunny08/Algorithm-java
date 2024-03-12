package 기출.카카오;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 카카오: 순위 검색
public class 순위검색 {

    static List<Applicant> applicants = new ArrayList<>();
    static class Applicant {
        String language, type, experience, food;
        int score;

        public Applicant(String language, String type, String experience, String food, int score) {
            this.language = language;
            this.type = type;
            this.experience = experience;
            this.food = food;
            this.score = score;
        }

        public boolean isPass(String query) {
            String[] q = query.split(" ");
            boolean lang = (Objects.equals(q[0], "-")) || (Objects.equals(q[0], this.language));
            boolean type = (Objects.equals(q[2], "-")) || (Objects.equals(q[2], this.type));
            boolean exp = (Objects.equals(q[4], "-")) || (Objects.equals(q[4], this.experience));
            boolean food = (Objects.equals(q[6], "-")) || (Objects.equals(q[6], this.food));
            boolean score = Integer.parseInt(q[7]) <= this.score;
            return lang && type && exp && food && score;
        }
    }

    public static int[] solution(String[] info, String[] query) {
        for (int i = 0; i < info.length; i++) {
            String[] one = info[i].split(" ");
            applicants.add(new Applicant(one[0], one[1], one[2], one[3], Integer.parseInt(one[4])));
        }
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int cnt = 0;
            for (Applicant a : applicants) {
                if (a.isPass(query[i])) cnt++;
            }
            answer[i] = cnt;
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        int[] ans = solution(info, query);
        for (int a : ans) {
            System.out.println(a);
        }
    }
}
