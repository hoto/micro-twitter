package twitter.io;

public class Command {
    public final String user;
    public final Function function;

    Command(String user, Function function) {
        this.user = user;
        this.function = function;
    }
}
