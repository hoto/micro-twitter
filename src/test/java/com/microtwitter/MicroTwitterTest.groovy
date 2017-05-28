package com.microtwitter

import spock.lang.Specification

class MicroTwitterTest extends Specification {
    private static Iterator<String> originalStdin
    private static PrintStream originalStdout
    private final Iterator<String> stdin = Mock(Iterator)
    private final ByteArrayOutputStream stdout = new ByteArrayOutputStream()

    def setup() {
        originalStdin = MicroTwitter.stdin
        originalStdout = MicroTwitter.stdout
        MicroTwitter.stdin = stdin
        MicroTwitter.stdout = new PrintStream(stdout)
    }

    def cleanup() {
        MicroTwitter.stdin = originalStdin
        MicroTwitter.stdout = originalStdout
    }

    def 'should print help when started and close on exit command'() {
        when:
        MicroTwitter.main()

        then:
        stdin.next() >> 'exit'
        stdout.toString() == 'Enter command (or exit to close):\n'
    }
}
