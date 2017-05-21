package twitter.io;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public Command parse(String command) {
        String[] tokens = command.split(" ");
        String user = tokens[0];
        if (tokens.length == 2) {
            String function = tokens[1];
            if (function.equals("->"))
                return new Command(user, Function.POST, "");
        } else if (tokens.length > 2) {
            String function = tokens[1];
            List<String> payloadList = Arrays.asList(tokens).subList(2, tokens.length);
            String payload = String.join(" ", payloadList);
            if (function.equals("follows"))
                return new Command(user, Function.FOLLOW, payload);
        }
        return new Command(user, Function.READ, "");
    }
}
