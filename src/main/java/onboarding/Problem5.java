package onboarding;

import java.util.ArrayList;
import java.util.List;

public class Problem5 {
    private static final List<Integer> UNITS = List.of(50_000, 10_000, 5_000, 500, 1_000, 100, 50, 10, 1);

    public static List<Integer> solution(int money) {
        List<Integer> answer = new ArrayList<>(UNITS.size());

        for (int unit : UNITS) {
            int unitCount = money / unit;

            answer.add(unitCount);
            money -= unit * unitCount;
        }
        return answer;
    }
}
