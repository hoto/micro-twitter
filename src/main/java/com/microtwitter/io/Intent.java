package com.microtwitter.io;

public enum Intent {
    POST("->"),
    READ(""),
    FOLLOW("follows"),
    WALL("wall");

    public final String inNaturalLanguage;

    Intent(String inNaturalLanguage) {
        this.inNaturalLanguage = inNaturalLanguage;
    }
}
