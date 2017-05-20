import spock.lang.Specification
import twitter.Help

class HelpTest extends Specification {
    def 'should return help message when run'() {
        setup:
        Help help = new Help()

        when:
        String helpMessage = help.getHelp()

        then:
        helpMessage != null
    }
}
