package com.microtwitter

import com.microtwitter.io.Console
import com.microtwitter.time.FixedClock
import spock.lang.Specification

import java.util.concurrent.TimeUnit

import static java.util.concurrent.TimeUnit.SECONDS

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

    def 'should display a wall'() {
        when:
        twitter.start()

        then:
        1 * console.getInput() >> 'Alice -> Hi'
        1 * console.getInput() >> { clock.setMillis(SECONDS.toMillis(3)); 'Alice' }
        1 * console.getInput() >> 'exit'
        1 * console.writeOutput('Enter command (exit to close):')
        1 * console.writeOutput('Alice - Hi (3 seconds ago)')
    }
}
