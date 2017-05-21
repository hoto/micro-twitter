package twitter.io;

public class Parser {
    public Command parse(String command) {
        return new Command(command);
    }
}
