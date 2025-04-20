
import java.util.*;  
import java.time.*;  

class UserVoteManager {
    private static HashMap<String, LocalDate> userVoteDates = new HashMap<>();

    // Checks if user can vote today
    public static boolean canVote(String username) {

        // finds CST time
        ZonedDateTime nowCST = ZonedDateTime.now(ZoneId.of("America/Chicago"));
        int day = nowCST.getDayOfMonth();
        if (day != 18) {
            System.out.println("Voting is only on the 18th of the month! MEOW");
            return false;
        }

        LocalDate lastVoted = userVoteDates.get(username);
        if (lastVoted != null && lastVoted.equals(nowCST.toLocalDate())) {
            System.out.println("User has already voted today.");
            return false;
        }

        return true;
    }

    public static void recordVote(String username) {
        LocalDate today = LocalDate.now(ZoneId.of("America/Chicago"));
        userVoteDates.put(username, today);
    }
}