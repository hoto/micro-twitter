package com.microtwitter.commands;

import spock.lang.Specification;

class CommandFactoryTest extends Specification {
    private CommandFactory commandFactory

    def setup() {
        commandFactory = new CommandFactory()
    }

    def 'should return read command'() {
        when:
        Command command = commandFactory.create('Bob', '', '')

        then:
        command.class == ReadCommand
    }

    def 'should return post command'() {
        when:
        Command command = commandFactory.create('Bob', '->', 'Good game though.')

        then:
        command.class == PostCommand
    }


    def 'should return follow command'() {
        when:
        Command command = commandFactory.create('Charlie', 'follows', 'Bob')

        then:
        command.class == FollowCommand
    }

    def 'should return wall command'() {
        when:
        Command command = commandFactory.create('Charlie', 'wall', '')

        then:
        command.class == WallCommand
    }
}
