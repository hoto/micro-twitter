package twitter.io;

public enum Function {
    POST("->"),
    READ(""),
    FOLLOW("follows"),
    WALL("wall");

    public final String inNaturalLanguage;

    Function(String inNaturalLanguage) {
        this.inNaturalLanguage = inNaturalLanguage;
    }
}
