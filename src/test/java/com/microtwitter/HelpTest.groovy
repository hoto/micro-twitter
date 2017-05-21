package com.microtwitter

import spock.lang.Specification

class HelpTest extends Specification {
    def 'should return help message when run'() {
        setup:
        Help help = new Help()

        when:
        String helpMessage = help.getHelp()

        then:
        helpMessage != null
    }
}
