package twitter.io;

public class Parser {
    public Command parse(String command) {
        String[] tokens = command.split(" ");
        String user = tokens[0];
        if (tokens.length == 2) {
            String function = tokens[1];
            if (function.equals("->"))
                return new Command(user, Function.POST);
        }
        return new Command(user, Function.READ);
    }
}
