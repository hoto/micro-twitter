package twitter.io;

public class Command {
    public final String user;
    public final Function function;
    public final String payload;

    Command(String user, Function function, String payload) {
        this.user = user;
        this.function = function;
        this.payload = payload;
    }
}
