# Micro Twitter

A console-based micro scale twitter in Java.

On windows use `gradlew.bat` instead of `./gradlew` to build.

### Usage:

    <user> -> <message>                Post a message as a user.
    <user>                             Read user timeline.
    <user> follows <another_user>      Follow another user.
    <user> wall                        View aggregated list of all subscriptions.

Example:

    Alice -> I love the weather today
    Alice
    Bob -> I'm in New York today!
    Bob follows Alice
    Bob wall


### Test

    ./gradlew test
    
### Run

    ./gradlew jar
    java -classpath ./build/libs/micro-twitter.jar com.microtwitter.MicroTwitter