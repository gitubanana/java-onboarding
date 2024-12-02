package onboarding;

public class Problem2 {
    public static String solution(String cryptogram) {
        StringBuilder result = new StringBuilder(cryptogram.length());

        for (int i = 0; i < cryptogram.length(); i++) {
            int resultLength = result.length();
            char toAppend = cryptogram.charAt(i);

            if (resultLength == 0) {
                result.append(toAppend);
                continue;
            }

            char lastCh = result.charAt(resultLength - 1);
            if (lastCh == toAppend) {
                result.delete(resultLength - 1, resultLength);
                i = moveTillConsecutiveLastCharacter(i, lastCh, cryptogram);
                continue;
            }

            result.append(toAppend);
        }
        return result.toString();
    }

    private static int moveTillConsecutiveLastCharacter(int i, char lastCh, String cryptogram) {
        for (; i < cryptogram.length(); i++) {
            if (cryptogram.charAt(i) != lastCh) {
                break;
            }
        }
        return i - 1;
    }
}
