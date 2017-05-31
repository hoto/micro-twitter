# Micro Twitter

A console-based micro twitter in Java.

### Usage:

    <user> -> <message>                Post a message as a user.
    <user>                             Read user timeline.
    <user> follows <another_user>      Follow another user.
    <user> wall                        View aggregated list of all subscriptions.

Example:

    $ Alice -> I love the weather today
    $ Alice
    Alice - I love the weather today (3 seconds ago)
    $ Bob -> I'm in New York today!
    $ Bob follows Alice
    $ Bob wall
    Bob - I'm in New York today! (4 seconds ago)
    Alice - I love the weather today (19 seconds ago)

    
### Run

On windows use `gradlew.bat` instead of `./gradlew` to build.
You can also install gradle globally and then run just `gradle` and ignore `gradlew` script.

    ./gradlew clean jar
    java -classpath ./build/libs/micro-twitter.jar com.microtwitter.MicroTwitter
    
### Test

    ./gradlew clean test