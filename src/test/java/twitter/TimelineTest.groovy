package twitter

import spock.lang.Specification

class TimelineTest extends Specification {
    private Timeline timeline
    private FixedClock clock

    def setup() {
        clock = new FixedClock()
        timeline = new Timeline(clock)
    }

    def 'should have no messages when user has no messages'() {
        when:
        List<Message> aliceMessages = timeline.view('Alice')

        then:
        aliceMessages == []
    }

    def 'should store message when user posts a message'() {
        given:
        timeline.post('Alice', 'I love the weather today.')

        when:
        List<Message> aliceTimeline = timeline.view('Alice')

        then:
        aliceTimeline.get(0).text == 'I love the weather today.'
    }

    def 'should store two messages when user posts same message twice'() {
        given:
        timeline.post('Alice', 'I love the weather today.')
        timeline.post('Alice', 'I love the weather today.')

        when:
        List<Message> aliceTimeline = timeline.view('Alice')

        then:
        aliceTimeline.get(0).text == 'I love the weather today.'
        aliceTimeline.get(1).text == 'I love the weather today.'
    }

    def 'should store two messages when user posts two messages'() {
        given:
        timeline.post('Bob', 'Damn! We lost!')
        timeline.post('Bob', 'Good game though.')

        when:
        List<Message> bobTimeline = timeline.view('Bob')

        then:
        bobTimeline.get(0).text == 'Damn! We lost!'
        bobTimeline.get(1).text == 'Good game though.'
    }

    def 'should store two messages from two different users'() {
        given:
        timeline.post('Alice', 'I love the weather today.')
        timeline.post('Bob', 'Damn! We lost!')

        when:
        List<Message> aliceTimeline = timeline.view('Alice')
        List<Message> bobTimeline = timeline.view('Bob')

        then:
        aliceTimeline.get(0).text == 'I love the weather today.'
        bobTimeline.get(0).text == 'Damn! We lost!'
    }

    def 'should store three messages from two different users'() {
        given:
        timeline.post('Alice', 'I love the weather today.')
        timeline.post('Bob', 'Damn! We lost!')
        timeline.post('Bob', 'Good game though.')

        when:
        List<Message> aliceTimeline = timeline.view('Alice')
        List<Message> bobTimeline = timeline.view('Bob')

        then:
        aliceTimeline.get(0).text == 'I love the weather today.'
        bobTimeline.get(0).text == 'Damn! We lost!'
        bobTimeline.get(1).text == 'Good game though.'
    }

    def 'should store timestamp with the message when user posts'() {
        given:
        clock.setMillis(123456)
        timeline.post('Alice', 'I love the weather today.')

        when:
        List<Message> aliceTimeline = timeline.view('Alice')

        then:
        aliceTimeline.get(0).text == 'I love the weather today.'
        aliceTimeline.get(0).timestamp == 123456
    }
}
