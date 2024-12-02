package onboarding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

class FriendsMap {
    private final Map<String, List<String>> friendsByName = new HashMap<>();

    public FriendsMap(List<List<String>> friends) {
        for (List<String> relationship : friends) {
            add(relationship.get(0), relationship.get(1));
            add(relationship.get(1), relationship.get(0));
        }
    }

    private void add(String name, String friend) {
        List<String> friends = friendsByName.getOrDefault(name, new ArrayList<>());

        friends.add(friend);
        friendsByName.put(name, friends);
    }

    public List<String> get(String name) {
        return List.copyOf(friendsByName.get(name));
    }
}

class ScoreMap {
    private final Map<String, Integer> scoreByName = new HashMap<>();

    public void add(String name, int addAmount) {
        scoreByName.put(name, scoreByName.getOrDefault(name, 0) + addAmount);
    }

    public List<String> top(int recommendCount) {
        return scoreByName.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .sorted(ScoreMap::compare)
                .map(Entry::getKey)
                .limit(recommendCount)
                .collect(Collectors.toList());
    }

    private static int compare(Entry<String, Integer> a, Entry<String, Integer> b) {
        if (a.getValue().equals(b.getValue())) {
            return a.getKey().compareTo(b.getKey());
        }
        return b.getValue().compareTo(a.getValue());
    }
}

public class Problem7 {
    private static final int FRIEND_POINT = 10;
    private static final int VISITOR_POINT = 1;
    private static final int RECOMMEND_COUNT = 5;

    public static List<String> solution(String me, List<List<String>> friends, List<String> visitors) {
        FriendsMap friendsMap = new FriendsMap(friends);
        ScoreMap scoreMap = new ScoreMap();
        Set<String> users = new HashSet<>();
        List<String> myFriends = friendsMap.get(me);

        addFriendsToUsers(users, friends);
        for (String user : users) {
            if (user.equals(me)) {
                continue;
            }

            scoreMap.add(user, countSameFriends(myFriends, friendsMap.get(user)) * FRIEND_POINT);
        }

        for (String visitor : visitors) {
            if (users.contains(visitor)) {
                continue;
            }
            scoreMap.add(visitor, VISITOR_POINT);
        }

        return scoreMap.top(RECOMMEND_COUNT);
    }

    private static void addFriendsToUsers(Set<String> users, List<List<String>> friends) {
        for (List<String> relationship : friends) {
            users.add(relationship.get(0));
            users.add(relationship.get(1));
        }
    }

    private static int countSameFriends(List<String> a, List<String> b) {
        return (int) a.stream()
                .filter(b::contains)
                .count();
    }
}
