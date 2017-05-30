import com.gargoylesoftware.htmlunit.html.HtmlPage;

public interface HtmlPageVerifier {
    void verify(HtmlPage page) throws Exception;
}
