package twitter;

public class Help {
    public String getHelp() {
        String format = "%-35s%s%n";
        return "Usage:\n" +
                String.format(format, "<user> -> <message>", "Post a message as a user.") +
                String.format(format, "<user>", "Read user timeline.") +
                String.format(format, "<user> follows <another_user>", "Follow another user.") +
                String.format(format, "<user> wall", "View aggregated list of all subscriptions.");
    }
}
