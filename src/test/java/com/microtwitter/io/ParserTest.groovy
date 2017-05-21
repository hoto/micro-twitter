package com.microtwitter.io

import spock.lang.Specification
import spock.lang.Unroll

class ParserTest extends Specification {
    private Parser parser

    def setup() {
        parser = new Parser()
    }

    @Unroll
    def 'should detect user, intent and payload from all valid commands'() {
        given:
        Command command = parser.parse(rawCommand)

        expect:
        command.user == user
        command.intent == function
        command.payload == payload

        where:
        rawCommand                 | user      | function      | payload
        'Bob'                      | 'Bob'     | Intent.READ   | ''
        'Bob -> Good game though.' | 'Bob'     | Intent.POST   | 'Good game though.'
        'Charlie follows Bob'      | 'Charlie' | Intent.FOLLOW | 'Bob'
        'Charlie wall'             | 'Charlie' | Intent.WALL   | ''
    }

    def 'should detect user is Bob when command is: Bob'() {
        when:
        Command command = parser.parse('Bob')

        then:
        command.user == 'Bob'
    }

    def 'should detect intent is reading when command is: Bob'() {
        when:
        Command command = parser.parse('Bob')

        then:
        command.intent == Intent.READ
    }

    def 'should detect user is Bob when command is: Bob -> Good game though.'() {
        when:
        Command command = parser.parse('Bob -> Good game though.')

        then:
        command.user == 'Bob'
    }

    def 'should detect intent is posting when command is: Bob -> Good game though.'() {
        when:
        Command command = parser.parse('Bob -> Good game though.')

        then:
        command.intent == Intent.POST
    }

    def 'should detect user is Charlie when command is: Charlie follows Bob'() {
        when:
        Command command = parser.parse('Charlie follows Bob')

        then:
        command.user == 'Charlie'
    }

    def 'should detect intent is to follow when command is: Charlie follows Bob'() {
        when:
        Command command = parser.parse('Charlie follows Bob')

        then:
        command.intent == Intent.FOLLOW
    }

    def 'should detect Bob is the followee when command is: Charlie follows Bob'() {
        when:
        Command command = parser.parse('Charlie follows Bob')

        then:
        command.payload == 'Bob'
    }

    def 'should detect intent is wall when command is: Charlie wall'() {
        when:
        Command command = parser.parse('Charlie wall')

        then:
        command.intent == Intent.WALL
    }
}
