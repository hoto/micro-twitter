# Micro Twitter

A console-based micro twitter in Java.

### Usage:

    <user> -> <message>                Post a message as a user.
    <user>                             Read user timeline.
    <user> follows <another_user>      Follow another user.
    <user> wall                        View aggregated list of all subscriptions.
    exit                               Exit the application.

Example:

    > Alice -> I love the weather today
    > Alice
    Alice - I love the weather today (3 seconds ago)
    > Bob -> I'm in New York today!
    > Bob follows Alice
    > Bob wall
    Bob - I'm in New York today! (8 seconds ago)
    Alice - I love the weather today (17 seconds ago)
    > exit

![Example](https://raw.github.com/hoto/micro-twitter/.screens/01_example.png)

### Run

On windows use `gradlew.bat` instead of `./gradlew` to build.
You can also install gradle globally and then run just `gradle` and ignore `gradlew` script.

    ./gradlew clean jar
    java -classpath ./build/libs/micro-twitter.jar com.microtwitter.MicroTwitter
    
### Test

    ./gradlew clean test
