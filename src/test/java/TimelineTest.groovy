import spock.lang.Specification
import twitter.Timeline

class TimelineTest extends Specification {
    private Timeline timeline

    def setup() {
        timeline = new Timeline()
    }

    def 'should have no messages when user has no messages'() {
        when:
        List<String> aliceMessages = timeline.view('Alice')

        then:
        aliceMessages == []
    }

    def 'should store message when user posts a message'() {
        given:
        timeline.post('Alice', 'I love the weather today.')

        when:
        List<String> bobTimeline = timeline.view('Alice')

        then:
        bobTimeline == ['I love the weather today.']
    }

    def 'should store two messages when user posts same message twice'() {
        given:
        timeline.post('Alice', 'I love the weather today.')
        timeline.post('Alice', 'I love the weather today.')

        when:
        List<String> aliceTimeline = timeline.view('Alice')

        then:
        aliceTimeline == [
                'I love the weather today.',
                'I love the weather today.'
        ]
    }

    def 'should store two messages when user posts two messages'() {
        given:
        timeline.post('Bob', 'Damn! We lost!')
        timeline.post('Bob', 'Good game though.')

        when:
        List<String> bobTimeline = timeline.view('Bob')

        then:
        bobTimeline == ['Damn! We lost!', 'Good game though.']
    }

    def 'should store two messages from two different users'() {
        given:
        timeline.post('Alice', 'I love the weather today.')
        timeline.post('Bob', 'Damn! We lost!')

        when:
        List<String> aliceTimeline = timeline.view('Alice')
        List<String> bobTimeline = timeline.view('Bob')

        then:
        aliceTimeline == ['I love the weather today.']
        bobTimeline == ['Damn! We lost!']
    }
}
