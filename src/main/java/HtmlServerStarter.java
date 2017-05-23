import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ThreadLocalRandom;

public class HtmlServerStarter {

    public final HtmlServer htmlServer;

    public HtmlServerStarter() {
        int htmlServerPort = nextFreeRandomPort();
        this.htmlServer = new HtmlServer(htmlServerPort);
        this.htmlServer.start();
    }

    private int nextFreeRandomPort() {
        int from = 49152;
        int to = 65535;
        while (true) {
            int randomPort = ThreadLocalRandom.current().nextInt(from, to);
            if (localPortFree(randomPort)) {
                return randomPort;
            }
        }
    }

    private boolean localPortFree(int port) {
        try {
            new ServerSocket(port).close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
