package twitter.io;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public Command parse(String command) {
        String[] tokens = command.split(" ");
        String user = tokens[0];
        if (isRead(tokens)) {
            return readCommand(user);
        }
        if (isPostOrFollow(tokens)) {
            String function = tokens[1];
            String payload = getPayload(tokens);
            if (function.equals("->")) {
                return new Command(user, Function.POST, payload);
            }
            if (function.equals("follows")) {
                return new Command(user, Function.FOLLOW, payload);
            }
        }
        return readCommand(user);
    }

    private boolean isRead(String[] tokens) {
        return tokens.length == 1;
    }

    private String getPayload(String[] tokens) {
        List<String> payloadList = Arrays.asList(tokens).subList(2, tokens.length);
        return String.join(" ", payloadList);
    }

    private boolean isPostOrFollow(String[] tokens) {
        return tokens.length > 2;
    }

    private Command readCommand(String user) {
        return new Command(user, Function.READ, "");
    }
}
