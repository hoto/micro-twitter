package twitter.io;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private final String DELIMITER = " ";
    private final int USER = 0;
    private final int INTENT = 1;
    private final int PAYLOAD = 2;
    private final String EMPTY_PAYLOAD = "";

    /**
     * Parses a command from natural language to a structure.
     */
    public Command parse(String command) {
        String[] tokens = command.split(DELIMITER);
        String user = tokens[USER];
        if (isWall(tokens)) {
            return wallCommand(user);
        }
        if (hasPayload(tokens)) {
            String intent = tokens[INTENT];
            String payload = getPayload(tokens);
            if (isPost(intent)) {
                return postCommand(user, payload);
            }
            if (isFollow(intent)) {
                return followCommand(user, payload);
            }
        }
        return readCommand(user);
    }

    private boolean isWall(String[] tokens) {
        return tokens.length == 2;
    }

    private Command wallCommand(String user) {
        return new Command(user, Intent.WALL, EMPTY_PAYLOAD);
    }

    private boolean hasPayload(String[] tokens) {
        return tokens.length > PAYLOAD;
    }

    private String getPayload(String[] tokens) {
        List<String> payloadList = Arrays.asList(tokens).subList(PAYLOAD, tokens.length);
        return String.join(DELIMITER, payloadList);
    }

    private boolean isFollow(String intent) {
        return Intent.FOLLOW.inNaturalLanguage.equals(intent);
    }

    private Command postCommand(String user, String payload) {
        return new Command(user, Intent.POST, payload);
    }

    private boolean isPost(String intent) {
        return Intent.POST.inNaturalLanguage.equals(intent);
    }

    private Command followCommand(String user, String payload) {
        return new Command(user, Intent.FOLLOW, payload);
    }

    private Command readCommand(String user) {
        return new Command(user, Intent.READ, EMPTY_PAYLOAD);
    }
}
