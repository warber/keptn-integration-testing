package sh.keptn.integrationtesting.environment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloader {


    public Path downloadToFile(String url, String fileName) throws IOException {
        URL downloadFileUrl = new URL(url);
        Path tmpDir = Files.createTempDirectory("env");
        Path absFileName = Paths.get(tmpDir.toString(), fileName);
        doDownloadFile(downloadFileUrl, absFileName);
        return absFileName;
    }


    private void doDownloadFile(URL url, Path outputFileName) throws IOException {
        try (InputStream in = url.openStream();
             ReadableByteChannel rbc = Channels.newChannel(in);
             FileOutputStream fos = new FileOutputStream(outputFileName.toString())) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }

}
