import java.util.HashMap;
import java.util.Map;


    class VotingSystem {
    private static HashMap<String, Integer> catVotes = new HashMap<>();

    public static void vote(String username, String catName) {
        if (!UserVoteManager.canVote(username)) {
            return;
        }

        // Add / update vote count for the cat
        catVotes.put(catName, catVotes.getOrDefault(catName, 0) + 1);
        UserVoteManager.recordVote(username);
        System.out.println("Vote recorded for " + catName + " by " + username);
    }

    public static void showVotes() {
        System.out.println("Current Vote amount:");
        for (Map.Entry<String, Integer> entry : catVotes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " votes");
        }
    }
}
