import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class WhenFindingButton {

    @Test
    public void view_page_will_not_show_wanted_button_right_after_page_load() throws Exception {
        new WebSiteVerifier().verifyHumanReadableContentAfterStartingServer(page -> {
            assertNull(page.getElementById("send_button"));
        });
    }

    @Test
    public void view_page_will_show_wanted_button_after_three_secs_page_load() throws Exception {
        new WebSiteVerifier().verifyHumanReadableContentAfterStartingServer(page -> {
            Thread.sleep(3000);
            assertNotNull(page.getElementById("send_button"));
        });
    }

    @Test
    public void after_clicking_button_new_element_text_appears() throws Exception {

        new WebSiteVerifier().verifyHumanReadableContentAfterStartingServer(page -> {

            Thread.sleep(3000);
            assertThat(page.asText(), not(containsString("Button clicked!")));

            page.getElementById("send_button").click();

            Thread.sleep(1000);
            assertThat(page.asText(), containsString("Button clicked!"));

        });
    }

}
