package sh.keptn.integrationtesting.environment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author warber
 **/
@ExtendWith(MockitoExtension.class)
class K3dLocalInstallerTest {


    @Mock
    private FileDownloader downloader;


    @Test
    public void testInstall() throws Exception {

        URL url = getClass().getClassLoader().getResource("bin/success");
        Path p = new File(url.toURI()).toPath();
        Mockito.when(downloader.downloadToFile(Mockito.anyString(), Mockito.anyString())).thenReturn(p);
        K3dLocalInstaller instance = new K3dLocalInstaller(downloader);
        instance.install();
    }

    @Test
    public void testWhenBinaryNotPresentThenInstallerThrowsException() throws Exception {
        Mockito.when(downloader.downloadToFile(Mockito.anyString(), Mockito.anyString())).thenReturn(Paths.get("WEIRDPATH"));
        K3dLocalInstaller instance = new K3dLocalInstaller(downloader);
        try {
            instance.install();
            Assertions.fail();
        } catch (EnvironmentException ee) {

        }
    }

    @Test
    public void testWhenInstallBinaryFailsThenInstallerThrowsException() throws Exception {

        URL url = getClass().getClassLoader().getResource("bin/error");
        Path p = new File(url.toURI()).toPath();
        Mockito.when(downloader.downloadToFile(Mockito.anyString(), Mockito.anyString())).thenReturn(p);
        K3dLocalInstaller instance = new K3dLocalInstaller(downloader);
        try {
            instance.install();
            Assertions.fail();
        } catch (EnvironmentException eie) {
        }
    }

}