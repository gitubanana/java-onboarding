package onboarding;

public class Problem3 {
    public static int solution(int endNumber) {
        int answer = 0;

        for (int checkNumber = 1; checkNumber <= endNumber; checkNumber++) {
            answer += count369(checkNumber);
        }
        return answer;
    }

    private static int count369(int number) {
        int count = 0;

        while (number > 0) {
            int digit = number % 10;
            if (is369(digit)) {
                count++;
            }
            number /= 10;
        }
        return count;
    }

    private static boolean is369(int number) {
        return number == 3 || number == 6 || number == 9;
    }
}
