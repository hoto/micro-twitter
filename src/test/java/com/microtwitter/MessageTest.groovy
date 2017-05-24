package com.microtwitter

import spock.lang.Specification

class MessageTest extends Specification {
    def 'messages should be sortable by time'() {
        given:
        Message message1 = new Message(new User(''), '', 1000)
        Message message2 = new Message(new User(''), '', 2000)
        Message message3 = new Message(new User(''), '', 3000)
        Message message4 = new Message(new User(''), '', 4000)

        and:
        List<Message> messages = Arrays.asList(message3, message2, message1, message4)

        when:
        Collections.sort(messages)

        then:
        messages.get(0).timestamp == 1000
        messages.get(1).timestamp == 2000
        messages.get(2).timestamp == 3000
        messages.get(3).timestamp == 4000
    }
}
