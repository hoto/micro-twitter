package com.microtwitter.messages

import spock.lang.Specification

class MessageTest extends Specification {
    def 'messages should be arranged chronologically by oldest first when sorted'() {
        given:
        Message message1 = new Message('', '', 2000)
        Message message2 = new Message('', '', 3000)
        Message message3 = new Message('', '', 1000)
        Message message4 = new Message('', '', 4000)

        and:
        List<Message> messages = Arrays.asList(message1, message2, message3, message4)

        when:
        Collections.sort(messages)

        then:
        messages.get(0).timestamp == 4000
        messages.get(1).timestamp == 3000
        messages.get(2).timestamp == 2000
        messages.get(3).timestamp == 1000
    }
}
