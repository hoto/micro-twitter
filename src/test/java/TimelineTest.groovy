import spock.lang.Specification
import twitter.Timeline

class TimelineTest extends Specification {
    private Timeline timeline

    def setup() {
        timeline = new Timeline()
    }

    def 'should return no messages when user has no messages'() {
        when:
        ArrayList<String> aliceMessages = timeline.view('Alice')

        then:
        aliceMessages == []
    }

    def 'should return message when user posts a message'() {
        given:
        timeline.post('Alice', 'I love the weather today.')

        when:
        ArrayList<String> aliceTimeline = timeline.view('Alice')

        then:
        aliceTimeline == ['I love the weather today.']
    }
}
