package twitter.io

import spock.lang.Specification

class ParserTest extends Specification {

    def 'should parse user from a command when only user name is in the input'() {
        given:
        Parser parser = new Parser()

        when:
        Command command = parser.parse('Alice')

        then:
        command.user == 'Alice'
    }

}
