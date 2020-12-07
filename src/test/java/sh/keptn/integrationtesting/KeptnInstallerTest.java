package sh.keptn.integrationtesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sh.keptn.integrationtesting.environment.FileDownloader;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;


@ExtendWith(MockitoExtension.class)
class KeptnInstallerTest {

    @Mock
    private FileDownloader fileDownloader;


    //TODO enhance test
    @Test
    public void test() throws Exception{

        URL url = this.getClass().getClassLoader().getResource("bin/echoargs.zip");
        Path p = new File(url.toURI()).toPath();
        Mockito.when(fileDownloader.downloadToFile(Mockito.anyString(), Mockito.anyString())).thenReturn(p);
        KeptnInstaller.KeptnInstallerBuilder builder = new KeptnInstaller.KeptnInstallerBuilder(fileDownloader);
        KeptnInstaller installer = builder//
        .hideSensitiveData(true) //
        .verbose(true)//
        .withChartRepo("a-chart-repo")//
        .withCredentials("cred.json")//
        .withEndpointServiceType("a-endpoint-service-type")//
        .withUseCase("a-usecase")//
        .build();
        installer.install();
    }


}