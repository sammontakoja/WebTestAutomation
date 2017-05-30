import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.Map;

public class WebSiteVerifier {

    public void verifyHumanReadableContentAfterStartingServer(HtmlPageVerifier verifier) throws Exception {

        HtmlServerStarter htmlServerStarter = new HtmlServerStarter();

        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage(url(htmlServerStarter.port));
            verifier.verify(page);
        }
    }

    public void verifyHumanReadableContentAfterStartingServer(Map<String,String> headers, HtmlPageVerifier verifier) throws Exception {

        HtmlServerStarter htmlServerStarter = new HtmlServerStarter();

        try (final WebClient webClient = new WebClient()) {
            headers.forEach((key, value) -> webClient.addRequestHeader(key, value));
            final HtmlPage page = webClient.getPage(url(htmlServerStarter.port));
            verifier.verify(page);
        }
    }

    private String url(int port) {
        return "http://localhost:" + port + "/view";
    }

}
