package com.microtwitter.io;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private final String DELIMITER = " ";
    private final int USER_OFFSET = 0;
    private final int INTENT_OFFSET = 1;
    private final int PAYLOAD_OFFSET = 2;
    private final String EMPTY_PAYLOAD = "";

    /**
     * Parses a command from natural language to a broken down structure.
     */
    public Command parse(String command) {
        String[] tokens = command.split(DELIMITER);
        String user = tokens[USER_OFFSET];
        String intent = tokens.length > 1 ? tokens[INTENT_OFFSET] : "";
        switch (intent) {
            case "wall": {
                return new Command(user, Intent.WALL, EMPTY_PAYLOAD);
            }
            case "->": {
                return new Command(user, Intent.POST, getPayload(tokens));
            }
            case "follows": {
                return new Command(user, Intent.FOLLOW, getPayload(tokens));
            }
            default: {
                return new Command(user, Intent.READ, EMPTY_PAYLOAD);
            }
        }
    }

    private String getPayload(String[] tokens) {
        List<String> payloadList = Arrays.asList(tokens).subList(PAYLOAD_OFFSET, tokens.length);
        return String.join(DELIMITER, payloadList);
    }

}
