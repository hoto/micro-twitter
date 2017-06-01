package com.microtwitter

import com.microtwitter.io.Console
import com.microtwitter.time.FixedClock
import spock.lang.Specification

import static java.util.concurrent.TimeUnit.MINUTES

class MicroTwitterTest extends Specification {
    private FixedClock clock
    private Console console
    private MicroTwitter twitter

    def setup() {
        clock = new FixedClock()
        console = Mock(Console)
        twitter = new MicroTwitter(clock, console)
    }

    def 'should print help when started and close on exit command'() {
        when:
        twitter.start()

        then:
        console.getInput() >> 'exit'
        1 * console.writeOutput('Enter command (exit to close):')
    }

    def 'should display a timeline'() {
        when:
        twitter.start()

        then:
        1 * console.writeOutput('Enter command (exit to close):')
        1 * console.getInput() >> { time(0); 'Alice -> I love the weather today' }
        1 * console.getInput() >> { time(5); 'Alice' }
        1 * console.writeOutput('Alice - I love the weather today (5 minutes ago)')
        1 * console.getInput() >> 'exit'
    }

    def 'should display a wall'() {
        when:
        twitter.start()

        then:
        1 * console.writeOutput('Enter command (exit to close):')
        1 * console.getInput() >> { time(0); 'Alice -> I love the weather today' }
        1 * console.getInput() >> { time(3); 'Bob -> Damn! We lost!' }
        1 * console.getInput() >> { time(4); 'Bob -> Good game though.' }

        1 * console.getInput() >> { time(5); 'Alice' }
        1 * console.writeOutput('Alice - I love the weather today (5 minutes ago)')

        1 * console.getInput() >> { time(5); 'Bob' }
        1 * console.writeOutput('Bob - Good game though. (1 minute ago)')
        1 * console.writeOutput('Bob - Damn! We lost! (2 minutes ago)')

        1 * console.getInput() >> { time(6); "Charlie -> I'm in New York today! Anyone want to have a coffee?" }

        1 * console.getInput() >> { time(7); "Charlie follows Alice" }
        1 * console.getInput() >> { time(8); "Charlie wall" }
        1 * console.writeOutput('Alice - I love the weather today (8 minutes ago)')

        1 * console.getInput() >> { time(9); "Charlie follows Bob" }
        1 * console.getInput() >> { time(10); "Charlie wall" }
        1 * console.writeOutput("Charlie - I'm in New York today! Anyone want to have a coffee? (4 minutes ago)")
        1 * console.writeOutput('Bob - Good game though. (6 minutes ago)')
        1 * console.writeOutput('Bob - Damn! We lost! (7 minutes ago)')
        1 * console.writeOutput('Alice - I love the weather today (10 minutes ago)')
        1 * console.getInput() >> 'exit'
    }

    private time(long min) {
        clock.setMillis(MINUTES.toMillis(min))
    }
}
