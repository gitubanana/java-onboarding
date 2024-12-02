package onboarding;

import java.util.List;

class Problem1 {
    private static final int PAGE_COUNT = 2;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int FIRST_PAGE = 1;
    private static final int LAST_PAGE = 400;
    public static final int ERROR = -1;
    public static final int POBI_WON = 1;
    public static final int CRONG_WON = 2;
    public static final int TIE = 0;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        try {
            validate(pobi);
            validate(crong);
            return judgeWinner(pobi, crong);
        } catch (IllegalArgumentException e) {
            return ERROR;
        }
    }

    private static void validate(List<Integer> pages) {
        validatePageCount(pages);
        validatePageIsInRange(pages);
        validateLeftPageIsOdd(pages);
        validateRightPageIsLeftPagePlusOne(pages);
    }

    private static void validatePageCount(List<Integer> pages) {
        if (pages.size() != PAGE_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private static void validatePageIsInRange(List<Integer> pages) {
        if (outOfRange(pages.get(LEFT)) || outOfRange(pages.get(RIGHT))) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean outOfRange(int page) {
        return page < FIRST_PAGE || page > LAST_PAGE;
    }

    private static void validateLeftPageIsOdd(List<Integer> pages) {
        if ((pages.get(LEFT) & 1) != 1) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateRightPageIsLeftPagePlusOne(List<Integer> pages) {
        if (pages.get(LEFT) + 1 != pages.get(RIGHT)) {
            throw new IllegalArgumentException();
        }
    }

    private static int judgeWinner(List<Integer> pobi, List<Integer> crong) {
        final int pobiScore = calculateMaxScoreOf(pobi);
        final int crongScore = calculateMaxScoreOf(crong);

        if (pobiScore > crongScore) {
            return POBI_WON;
        }

        if (pobiScore < crongScore) {
            return CRONG_WON;
        }

        return TIE;
    }

    private static int calculateMaxScoreOf(List<Integer> pages) {
        return Math.max(scoreOf(pages.get(LEFT)), scoreOf(pages.get(RIGHT)));
    }

    private static int scoreOf(int page) {
        return Math.max(addEveryDigit(page), multiplyEveryDigit(page));
    }

    private static int addEveryDigit(int number) {
        int result = 0;

        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    private static int multiplyEveryDigit(int number) {
        int result = 1;

        while (number > 0) {
            result *= number % 10;
            number /= 10;
        }
        return result;
    }
}
