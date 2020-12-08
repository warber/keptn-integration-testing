package sh.keptn.integrationtesting;

import net.lingala.zip4j.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;
import org.zeroturnaround.exec.stream.slf4j.Slf4jStream;
import sh.keptn.integrationtesting.environment.EnvironmentException;
import sh.keptn.integrationtesting.environment.FileDownloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author warber
 **/
public class KeptnInstaller {

    private final Logger logger = LoggerFactory.getLogger(KeptnInstaller.class);

    public static final String KEPTN_INSTALL_REPO = "https://storage.googleapis.com/keptn-installer/latest/keptn-0.1.0.tgz";

    public static final String KEPTN_CLI_DOWNLOAD_URL = "https://storage.googleapis.com/keptn-cli/latest/keptn-linux.zip";

    private final FileDownloader fileDownloader;

    private final String chartRepo;

    private final String credentialsJson;

    private final String useCase;

    private final String endpointServiceType;

    private final boolean hideSensitiveData;

    private final boolean verbose;

    private Path keptnBinaryPath;

    private KeptnInstaller(FileDownloader fileDownloader, KeptnInstallerBuilder builder) {
        this.fileDownloader = fileDownloader;
        chartRepo = builder.chartRepo;
        credentialsJson = builder.credentialsJson;
        useCase = builder.useCase;
        endpointServiceType = builder.endpointServiceType;
        hideSensitiveData = builder.hideSensitiveData;
        verbose = builder.verbose;
    }

    //keptn install --chart-repo="${KEPTN_INSTALLER_REPO}" --creds=creds.json --verbose --use-case=continuous-delivery --endpoint-service-type=LoadBalancer --hide-sensitive-data
    void install() {
        try {
            logger.info("Downloading Keptn CLI...");
            Path zippedKeptnCliPath = fileDownloader.downloadToFile(KeptnInstaller.KEPTN_CLI_DOWNLOAD_URL, "keptn-cli.zip");
            Path keptnCliDir = Files.createTempDirectory("keptn-cli");
            logger.info("Extracting Keptn CLI...");
            new ZipFile(zippedKeptnCliPath.toFile()).extractAll(keptnCliDir.toString());
            this.keptnBinaryPath = Paths.get(keptnCliDir.toString(), "keptn");
            ProcessResult result = new ProcessExecutor().command(buildKeptnInstallCmd(keptnCliDir))//
                    .redirectOutput(Slf4jStream.of(getClass()).asInfo())//
                    .redirectError(Slf4jStream.of(getClass()).asError())//
                    .execute();

            if (result.getExitValue() != 0)
                throw new KeptnException();

        } catch (IOException | InterruptedException | TimeoutException e) {
            throw new KeptnException("Unable to install keptn", e);
        }
    }


    public void uninstall() {
        logger.info("Uninstalling...");
        if (keptnBinaryPath == null)
            throw new EnvironmentException("Unable to uninstall k3d environment. No k3d binary found. Did you install the k3d environment?");

        try {
            ProcessResult result = new ProcessExecutor().command(keptnBinaryPath.toString(), "uninstall")//
                    .redirectOutput(Slf4jStream.of(getClass()).asInfo())//
                    .redirectError(Slf4jStream.of(getClass()).asError())//
                    .execute();
            if (result.getExitValue() != 0) throw new KeptnException("Unable to uninstall keptn");

        } catch (IOException | TimeoutException | InterruptedException e) {
            throw new KeptnException("Unable to uninstall keptn", e);
        }
    }

    private List<String> buildKeptnInstallCmd(Path keptnCliDir) {

        List<String> cmd = new ArrayList<>();
        cmd.add(keptnCliDir.toString() + "/keptn");
        cmd.add("install");
        if (chartRepo != null) cmd.add(String.format("--chart-repo=%s", this.chartRepo));
        if (credentialsJson != null) cmd.add(String.format("--creds=%s", credentialsJson));
        if (verbose) cmd.add("--verbose");
        if (useCase != null) cmd.add(String.format("--use-case=%s", useCase));
        if (endpointServiceType != null) cmd.add(String.format("--endpoint-service-type=", endpointServiceType));
        if (hideSensitiveData) cmd.add("--hide-sensitive-data");
        return cmd;
    }

    /**
     * @author warber
     **/
    public static class KeptnInstallerBuilder {

        private final FileDownloader fileDownloader;

        private String chartRepo;

        private String credentialsJson;

        private String useCase;

        private String endpointServiceType;

        private boolean hideSensitiveData;

        private boolean verbose;


        public static KeptnInstallerBuilder create() {
            return new KeptnInstallerBuilder(new FileDownloader());
        }

        public static KeptnInstallerBuilder createContinuousDelivery() {
            return KeptnInstaller.KeptnInstallerBuilder.create()//
                    .hideSensitiveData(true)//
                    .verbose(true)//
                    .withChartRepo(KeptnInstaller.KEPTN_INSTALL_REPO)//
                    .withUseCase("continuous-delivery")//
                    .withEndpointServiceType("LoadBalancer");
        }

        KeptnInstallerBuilder(FileDownloader fileDownloader) {
            this.fileDownloader = fileDownloader;
        }

        public KeptnInstallerBuilder withChartRepo(String chartRepo) {
            this.chartRepo = chartRepo;
            return this;
        }

        public KeptnInstallerBuilder withCredentials(String credentialsJson) {
            this.credentialsJson = credentialsJson;
            return this;
        }

        public KeptnInstallerBuilder withUseCase(String useCase) {
            this.useCase = useCase;
            return this;
        }

        public KeptnInstallerBuilder withEndpointServiceType(String endpointServiceType) {
            this.endpointServiceType = endpointServiceType;
            return this;
        }

        public KeptnInstallerBuilder hideSensitiveData(boolean hideSensitiveData) {
            this.hideSensitiveData = hideSensitiveData;
            return this;
        }

        public KeptnInstallerBuilder verbose(boolean verbose) {
            this.verbose = verbose;
            return this;
        }

        public KeptnInstaller build() {
            return new KeptnInstaller(fileDownloader, this);
        }
    }
}
