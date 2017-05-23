import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.stream.Collectors;

public class HtmlServer {

    private final HttpServer server;
    public final int port;
    private final byte[] responseBody;

    public HtmlServer(int htmlPort) {
        this.port = htmlPort;
        this.server = withHTMLResponse(this.port);
        this.responseBody = htmlContent().getBytes();
    }

    public void start() {
        server.start();
    }

    private HttpServer withHTMLResponse(int htmlPort) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(htmlPort), 0);
            httpServer.createContext("/view", httpExchange -> writeIndexHtmlToResponse(httpExchange));
            return httpServer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeIndexHtmlToResponse(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, responseBody.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(responseBody);
        os.close();
    }

    private String htmlContent() {
        InputStream indexHtml = HtmlServer.class.getClassLoader().getResourceAsStream("index.html");
        return new BufferedReader(new InputStreamReader(indexHtml))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) {
        HtmlServer server = new HtmlServer(8000);
        server.start();
    }

}