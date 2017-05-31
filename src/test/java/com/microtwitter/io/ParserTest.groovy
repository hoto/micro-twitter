package com.microtwitter.io

import com.microtwitter.commands.FollowCommand
import com.microtwitter.commands.PostCommand
import com.microtwitter.commands.ReadCommand
import com.microtwitter.commands.WallCommand
import spock.lang.Specification

class ParserTest extends Specification {
    private Parser parser

    def setup() {
        parser = new Parser()
    }

    def 'should return a read command'() {
        expect:
        parser.parse2('Bob') instanceof ReadCommand
    }

    def 'should return a post command'() {
        expect:
        parser.parse2('Bob -> Good game though.') instanceof PostCommand
    }

    def 'should return a follow command'() {
        expect:
        parser.parse2('Charlie follows Bob') instanceof FollowCommand
    }

    def 'should return a wall command'() {
        expect:
        parser.parse2('Charlie wall') instanceof WallCommand
    }
}
