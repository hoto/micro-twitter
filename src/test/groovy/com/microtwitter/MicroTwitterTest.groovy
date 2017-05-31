package com.microtwitter

import spock.lang.Specification

class MicroTwitterTest extends Specification {
    private Iterator<String> stdin
    private ByteArrayOutputStream stdout = new ByteArrayOutputStream()

    def setup() {
        stdin = Mock(Iterator)
        stdout = new ByteArrayOutputStream()
        MicroTwitter.stdin = stdin
        MicroTwitter.stdout = new PrintStream(stdout)
    }

    def 'should print help when started and close on exit command'() {
        when:
        MicroTwitter.main()

        then:
        stdin.next() >> 'exit'
        stdout.toString() == 'Enter command (or exit to close):\n'
    }
}
