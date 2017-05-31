package com.microtwitter.io

import com.microtwitter.commands.CommandFactory
import spock.lang.Specification
import spock.lang.Unroll

class ParserTest extends Specification {
    private Parser parser
    private CommandFactory commandFactory

    def setup() {
        commandFactory = Mock(CommandFactory)
        parser = new Parser(commandFactory)
    }

    @Unroll
    def 'should parse all valid commands'() {
        when:
        parser.parse(input)

        then:
        1 * commandFactory.create(userName, intent, payload)

        where:
        input                      || userName  | intent    | payload
        'Bob'                      || 'Bob'     | ''        | ''
        'Bob -> Good game though.' || 'Bob'     | '->'      | 'Good game though.'
        'Charlie follows Bob'      || 'Charlie' | 'follows' | 'Bob'
        'Charlie wall'             || 'Charlie' | 'wall'    | ''
    }
}
