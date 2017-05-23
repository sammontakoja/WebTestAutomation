import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class WhenFindingSpecialContent {

    @Test
    public void special_content_not_showing_without_dedicated_request_header() throws InterruptedException, IOException {

        HtmlServerStarter htmlServerStarter = new HtmlServerStarter();
        String url = "http://localhost:"+ htmlServerStarter.htmlServer.port + "/view";

        try (final WebClient webClient = new WebClient()) {
            Page page = webClient.getPage(url);
            assertThat(page.getWebResponse().getContentAsString(), not(containsString("Jippii!")));
        }
    }

    @Test
    public void special_content_showing_when_using_dedicated_request_header() throws InterruptedException, IOException {

        HtmlServerStarter htmlServerStarter = new HtmlServerStarter();
        String url = "http://localhost:"+ htmlServerStarter.htmlServer.port + "/view";

        try (final WebClient webClient = new WebClient()) {

            webClient.addRequestHeader("special_content", "true");

            Page page = webClient.getPage(url);
            assertThat(page.getWebResponse().getContentAsString(), containsString("Jippii!"));
        }
    }

}
