package twitter.io

import spock.lang.Specification
import spock.lang.Unroll

class ParserTest extends Specification {
    private Parser parser

    def setup() {
        parser = new Parser()
    }

    @Unroll
    def 'should detect user, function and payload from all possible commands'() {
        given:
        Command command = parser.parse(input)

        expect:
        command.user == user
        command.function == function
        command.payload == payload

        where:
        input                   | user      | function        | payload
        'Alice'                 | 'Alice'   | Function.READ   | ''
        'Alice ->'              | 'Alice'   | Function.POST   | ''
        'Charlie follows Alice' | 'Charlie' | Function.FOLLOW | 'Alice'
    }

    def 'should detect user is Alice when input is: Alice'() {
        when:
        Command command = parser.parse('Alice')

        then:
        command.user == 'Alice'
    }

    def 'should detect function is reading when input is: Alice'() {
        when:
        Command command = parser.parse('Alice')

        then:
        command.function == Function.READ
    }

    def 'should detect user is Alice when input is: Alice ->'() {
        when:
        Command command = parser.parse('Alice ->')

        then:
        command.user == 'Alice'
    }

    def 'should detect function is posting when input is: Alice ->'() {
        when:
        Command command = parser.parse('Alice ->')

        then:
        command.function == Function.POST
    }

    def 'should detect user is Charlie when input is: Charlie follows Alice'() {
        when:
        Command command = parser.parse('Charlie follows Alice')

        then:
        command.user == 'Charlie'
    }

    def 'should detect function is following when input is: Charlie follows Alice'() {
        when:
        Command command = parser.parse('Charlie follows Alice')

        then:
        command.function == Function.FOLLOW
    }

    def 'should detect Alice is the followee when input is: Charlie follows Alice'() {
        when:
        Command command = parser.parse('Charlie follows Alice')

        then:
        command.payload == 'Alice'
    }
}
