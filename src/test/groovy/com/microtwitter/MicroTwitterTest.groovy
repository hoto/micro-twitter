package com.microtwitter

import com.microtwitter.io.Console
import spock.lang.Specification

class MicroTwitterTest extends Specification {
    private Console console
    private ByteArrayOutputStream stdout
    private MicroTwitter twitter

    def setup() {
        stdout = new ByteArrayOutputStream()
        console = Mock(Console)
        twitter = new MicroTwitter(console)
        twitter.stdout = new PrintStream(stdout)
    }

    def 'should print help when started and close on exit command'() {
        when:
        twitter.start()

        then:
        console.getInput() >> 'exit'
        stdout.toString() == 'Enter command (exit to close):\n'
    }
}
