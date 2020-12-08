package sh.keptn.integrationtesting.environment;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sh.keptn.integrationtesting.utils.SuccessHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author warber
 **/
class FIleDownloaderTest {


    private static final int PORT = 6991;

    private static final String TEST_ENDPOINT = "/test";

    private HttpServer server;

    @BeforeEach
    public void setup() throws Exception {
        startServer();
        registerHandler(TEST_ENDPOINT, new SuccessHandler("A Response"));
    }

    @AfterEach
    public void tearDown() {
        stopServer();
    }

    @Test
    public void testDownloadFile() throws Exception {

        FileDownloader instance = new FileDownloader();
        Path file = instance.downloadToFile(String.format("http://localhost:%d%s", PORT, TEST_ENDPOINT),"testfile");
        Assertions.assertEquals("A Response\n", readFile(file));
    }

    @Test
    public void testDownloadFileFromInvalidUrl() throws Exception {
        FileDownloader instance = new FileDownloader();
        try {
            instance.downloadToFile("INVALID","testfile");
            Assertions.fail();
        } catch (MalformedURLException ioe) {

        }
    }

    private void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(FIleDownloaderTest.PORT), 0);
        server.setExecutor(null);
        server.start();
    }

    private void stopServer() {
        if (server != null)
            server.stop(0);
    }

    private void registerHandler(String uriToHandle, HttpHandler httpHandler) {
        server.createContext(uriToHandle, httpHandler);
    }

    private static String readFile(Path filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath.toString()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }


}