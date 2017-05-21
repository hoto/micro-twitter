package twitter.io;

import java.util.Arrays;
import java.util.List;

public class Parser {
    final int USER = 0;
    final int FUNCTION = 1;
    final int PAYLOAD = 2;

    public Command parse(String command) {
        String[] tokens = command.split(" ");
        String user = tokens[USER];
        if (hasPayload(tokens)) {
            String function = tokens[FUNCTION];
            String payload = getPayload(tokens);
            if (isPost(function)) {
                return postCommand(user, payload);
            }
            if (isFollow(function)) {
                return followCommand(user, payload);
            }
        }
        return readCommand(user);
    }

    private boolean hasPayload(String[] tokens) {
        return tokens.length > PAYLOAD;
    }

    private String getPayload(String[] tokens) {
        List<String> payloadList = Arrays.asList(tokens).subList(PAYLOAD, tokens.length);
        return String.join(" ", payloadList);
    }

    private boolean isFollow(String function) {
        return function.equals("follows");
    }


    private Command postCommand(String user, String payload) {
        return new Command(user, Function.POST, payload);
    }

    private boolean isPost(String function) {
        return function.equals("->");
    }

    private Command followCommand(String user, String payload) {
        return new Command(user, Function.FOLLOW, payload);
    }

    private Command readCommand(String user) {
        return new Command(user, Function.READ, "");
    }
}
