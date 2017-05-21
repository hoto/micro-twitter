package twitter.io

import spock.lang.Specification
import spock.lang.Unroll

class ParserTest extends Specification {
    private Parser parser

    def setup() {
        parser = new Parser()
    }

    @Unroll
    def 'should detect user, function and payload from all valid commands'() {
        given:
        Command command = parser.parse(input)

        expect:
        command.user == user
        command.function == function
        command.payload == payload

        where:
        input                      | user      | function        | payload
        'Bob'                      | 'Bob'     | Function.READ   | ''
        'Bob -> Good game though.' | 'Bob'     | Function.POST   | 'Good game though.'
        'Charlie follows Bob'      | 'Charlie' | Function.FOLLOW | 'Bob'
    }

    def 'should detect user is Bob when input is: Bob'() {
        when:
        Command command = parser.parse('Bob')

        then:
        command.user == 'Bob'
    }

    def 'should detect function is reading when input is: Bob'() {
        when:
        Command command = parser.parse('Bob')

        then:
        command.function == Function.READ
    }

    def 'should detect user is Bob when input is: Bob -> Good game though.'() {
        when:
        Command command = parser.parse('Bob -> Good game though.')

        then:
        command.user == 'Bob'
    }

    def 'should detect function is posting when input is: Bob -> Good game though.'() {
        when:
        Command command = parser.parse('Bob -> Good game though.')

        then:
        command.function == Function.POST
    }

    def 'should detect user is Charlie when input is: Charlie follows Bob'() {
        when:
        Command command = parser.parse('Charlie follows Bob')

        then:
        command.user == 'Charlie'
    }

    def 'should detect function is following when input is: Charlie follows Bob'() {
        when:
        Command command = parser.parse('Charlie follows Bob')

        then:
        command.function == Function.FOLLOW
    }

    def 'should detect Bob is the followee when input is: Charlie follows Bob'() {
        when:
        Command command = parser.parse('Charlie follows Bob')

        then:
        command.payload == 'Bob'
    }
}
