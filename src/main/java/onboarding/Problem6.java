package onboarding;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Problem6 {
    private static final int EMAIL = 0;
    private static final int NAME = 1;
    private static final int WORD_LENGTH = 2;

    public static List<String> solution(List<List<String>> forms) {
        Set<String> answer = new TreeSet<>();

        for (int i = 0; i < forms.size() - 1; i++) {
            List<String> a = forms.get(i);
            List<String> words = makeWords(a.get(NAME));

            for (int j = i + 1; j < forms.size(); j++) {
                List<String> b = forms.get(j);

                if (hasNameInWords(b.get(NAME), words)) {
                    answer.add(a.get(EMAIL));
                    answer.add(b.get(EMAIL));
                }
            }
        }
        return new ArrayList<>(answer);
    }

    private static List<String> makeWords(String str) {
        List<String> words = new ArrayList<>(str.length() - 1);

        for (int i = 0; i < str.length() - 1; i++) {
            words.add(str.substring(i, i + WORD_LENGTH));
        }
        return words;
    }

    private static boolean hasNameInWords(String name, List<String> words) {
        for (String word : words) {
            if (name.contains(word)) {
                return true;
            }
        }
        return false;
    }
}
