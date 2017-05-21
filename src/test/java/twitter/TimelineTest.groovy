package twitter

import spock.lang.Specification

class TimelineTest extends Specification {
    private Timeline timeline
    private FixedClock clock

    def setup() {
        clock = new FixedClock()
        timeline = new Timeline(clock)
    }

    def 'should have no messages when user never posted'() {
        when:
        List<Message> aliceMessages = timeline.view('Alice')

        then:
        aliceMessages == []
    }

    def 'should have message when user posted a message'() {
        given:
        timeline.post('Alice', 'I love the weather today.')

        when:
        List<Message> aliceTimeline = timeline.view('Alice')

        then:
        aliceTimeline.get(0).text == 'I love the weather today.'
    }

    def 'should have two messages when user posted two different messages'() {
        given:
        timeline.post('Bob', 'Damn! We lost!')
        timeline.post('Bob', 'Good game though.')

        when:
        List<Message> bobTimeline = timeline.view('Bob')

        then:
        bobTimeline.get(0).text == 'Damn! We lost!'
        bobTimeline.get(1).text == 'Good game though.'
    }

    def 'should have two messages when user posted same message twice'() {
        given:
        timeline.post('Alice', 'I love the weather today.')
        timeline.post('Alice', 'I love the weather today.')

        when:
        List<Message> aliceTimeline = timeline.view('Alice')

        then:
        aliceTimeline.get(0).text == 'I love the weather today.'
        aliceTimeline.get(1).text == 'I love the weather today.'
    }

    def 'should have two messages when two users posted a message each'() {
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

    def 'should have three messages when two users posted three messages'() {
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

    def 'should store timestamp with the message when user posted'() {
        given:
        clock.setMillis(1000)
        timeline.post('Alice', 'I love the weather today.')

        when:
        List<Message> aliceTimeline = timeline.view('Alice')

        then:
        aliceTimeline.get(0).text == 'I love the weather today.'
        aliceTimeline.get(0).timestamp == 1000
    }

    def 'should store timestamps with the messages when two users posted consecutively'() {
        given:
        clock.setMillis(1000)
        timeline.post('Alice', 'I love the weather today.')
        timeline.post('Bob', 'Damn! We lost!')
        clock.setMillis(2000)
        timeline.post('Bob', 'Good game though.')
        clock.setMillis(3000)
        timeline.post('Charlie', "I'm in New York today! Anyone want to have a coffee?")

        when:
        List<Message> aliceTimeline = timeline.view('Alice')
        List<Message> bobTimeline = timeline.view('Bob')
        List<Message> charlieTimeline = timeline.view('Charlie')

        then:
        aliceTimeline.get(0).text == 'I love the weather today.'
        aliceTimeline.get(0).timestamp == 1000
        bobTimeline.get(0).text == 'Damn! We lost!'
        bobTimeline.get(0).timestamp == 1000
        bobTimeline.get(1).text == 'Good game though.'
        bobTimeline.get(1).timestamp == 2000
        charlieTimeline.get(0).text == "I'm in New York today! Anyone want to have a coffee?"
        charlieTimeline.get(0).timestamp == 3000
    }
}
