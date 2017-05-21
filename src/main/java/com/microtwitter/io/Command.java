package com.microtwitter.io;

public class Command {
    public final String user;
    public final Intent intent;
    public final String payload;

    Command(String user, Intent intent, String payload) {
        this.user = user;
        this.intent = intent;
        this.payload = payload;
    }
}
