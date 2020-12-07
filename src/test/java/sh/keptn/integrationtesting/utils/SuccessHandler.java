package sh.keptn.integrationtesting.utils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

public class SuccessHandler implements HttpHandler {

    private String responseBody = "";
    private String contentType = "text/plain";

    public SuccessHandler() {}

    public SuccessHandler(String responseBody) {
        this.responseBody = responseBody;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", contentType);
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, responseBody.length());

        PrintWriter printWriter = new PrintWriter(exchange.getResponseBody());
        printWriter.print(responseBody);
        printWriter.close();
    }

}