package onboarding;

public class Problem4 {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String solution(String word) {
        StringBuilder answer = new StringBuilder(word.length());

        for (int i = 0; i < word.length(); i++) {
            answer.append(convertAlphabet(word.charAt(i)));
        }
        return answer.toString();
    }

    private static char convertAlphabet(char ch) {
        if (Character.isLowerCase(ch)) {
            return LOWERCASE.charAt(LOWERCASE.length() - (ch - 'a') - 1);
        }

        if (Character.isUpperCase(ch)) {
            return UPPERCASE.charAt(UPPERCASE.length() - (ch - 'A') - 1);
        }

        return ch;
    }
}
