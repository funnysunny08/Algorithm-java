package 기출.카카오;


// 카카오: 숫자 문자열과 영단어
public class 숫자문자열과영단어 {

    public static int solution(String s) {
        String[] number = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for (int i = 0; i <= 9; i++) {
            if (s.contains(number[i])) {
                s = s.replaceAll(number[i], Integer.toString(i));
            }
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        String s = "one4seveneight";
        System.out.println(solution(s));
    }
}
