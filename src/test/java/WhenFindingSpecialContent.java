import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class WhenFindingSpecialContent {

    @Test
    public void special_content_not_showing_without_dedicated_request_header() throws Exception {
        new WebSiteVerifier().verifyHumanReadableContentAfterStartingServer(page -> {
            assertThat(page.getWebResponse().getContentAsString(), not(containsString("Jippii!")));
        });
    }

    @Test
    public void special_content_showing_when_using_dedicated_request_header() throws Exception {


        Map<String, String> headers = new HashMap<String, String>()
        {{
            put("special_content", "true");
        }};

        new WebSiteVerifier().verifyHumanReadableContentAfterStartingServer(headers, page -> {
            assertThat(page.getWebResponse().getContentAsString(), containsString("Jippii!"));
        });

    }

}
