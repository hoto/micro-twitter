import org.junit.Test;
import twitter.Help;

import static org.junit.Assert.assertNotNull;

public class HelpTest {
    @Test
    public void should_display_help_when_run() {
        Help help = new Help();
        assertNotNull("Help message should not be empty.", help.getHelp());
    }
}
