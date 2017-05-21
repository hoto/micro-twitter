package twitter;

public class Message {
    public final String text;
    public final int timestamp;

    public Message(String text, int timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }
}
