package twitter.io

import spock.lang.Specification

class ParserTest extends Specification {
    private Parser parser;

    def setup() {
        parser = new Parser()
    }

    def 'should set user to Alice when input is: Alice'() {
        when:
        Command command = parser.parse('Alice')

        then:
        command.user == 'Alice'
    }

    def 'should set function to READ when input is: Alice'() {
        when:
        Command command = parser.parse('Alice')

        then:
        command.function == Function.READ
    }

    def 'should set user to Alice when input is: Alice ->'() {
        when:
        Command command = parser.parse('Alice ->')

        then:
        command.user == 'Alice'
    }

    def 'should set function to POST when input is: Alice ->'() {
        when:
        Command command = parser.parse('Alice ->')

        then:
        command.function == Function.POST
    }
}
