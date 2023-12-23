package 기출.카카오;

// 프로그래머스: 이모티콘 할인행사
public class 이모티콘할인행사 {

    /*
    * 일단, 이모티콘 플러스 가입자를 최대로 늘리고, 그게 안 된다면 판매액이라도 늘리자!
    * */

    public static int plusCount = -1;
    public static int money = -1;
    public static int[] percent;

    public static void dfs(int L, int[][] users, int[] emoticons) {
        if (L == emoticons.length) {
            calculate(users, emoticons);
            return;
        }
        for (int i = 10; i <= 40; i += 10) {
            percent[L] = i;
            dfs(L + 1, users, emoticons);
        }
    }

    public static int[] solution(int[][] users, int[] emoticons) {
        percent = new int[emoticons.length];
        dfs(0, users, emoticons);
        return new int[]{plusCount, money};
    }

    public static void calculate(int[][] users, int[] emoticons) {
        // 특정 할인율일 때의 결과를 계산한다!
        int[] spend = new int[users.length];
        int cnt = 0;
        int totalSpend = 0;
        for (int i = 0; i < users.length; i++) {
            for (int j = 0; j < emoticons.length; j++) {
                if (percent[j] >= users[i][0]) { // 할인율 비교
                    spend[i] += emoticons[j] * (100 - percent[j]) / 100;
                }
            }
            if (spend[i] >= users[i][1]) { // 이모티콘 플러스 구매!
                cnt++;
            } else {
                totalSpend += spend[i];
            }
        }
        if (cnt > plusCount) {
            plusCount = cnt;
            money = totalSpend;
        } else if (cnt == plusCount && totalSpend > money) {
            money = totalSpend;
        }
    }

    public static void main(String[] args) {
        int[][] users = {{40, 10000}, {25, 10000}};
        int[] emoticons = {7000, 9000};
        int[] answer = solution(users, emoticons);
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }
}
