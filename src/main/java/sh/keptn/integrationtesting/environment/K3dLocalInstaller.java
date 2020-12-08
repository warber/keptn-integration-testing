package sh.keptn.integrationtesting.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.ProcessResult;
import org.zeroturnaround.exec.stream.slf4j.Slf4jStream;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.TimeoutException;

/**
 * @author warber
 **/
public class K3dLocalInstaller implements Installer {

    private Logger logger = LoggerFactory.getLogger(K3dLocalInstaller.class);

    private static final String K3D_DOWNLOAD_URL = "https://github.com/rancher/k3d/releases/download/v3.4.0/k3d-linux-amd64";

    private static final String K3D_BINARY_NAME = "k3d";

    private Path k3dBinaryPath = null;

    private final FileDownloader fileDownloader;

    K3dLocalInstaller(FileDownloader fileDownloader) {

        this.fileDownloader = fileDownloader;
    }


    @Override
    public void install() throws EnvironmentException {

        logger.info("Installing...");
        try {
            k3dBinaryPath = fileDownloader.downloadToFile(K3dLocalInstaller.K3D_DOWNLOAD_URL, K3dLocalInstaller.K3D_BINARY_NAME);
            k3dBinaryPath.toFile().setExecutable(true);
            ProcessResult result = new ProcessExecutor().command(k3dBinaryPath.toString(), "cluster", "create", "mycluster")//
                    .redirectOutput(Slf4jStream.of(getClass()).asInfo())//
                    .redirectError(Slf4jStream.of(getClass()).asError())//
                    .execute();
            if (result.getExitValue() != 0) throw new EnvironmentException("Unable to install k3d environment");

        } catch (IOException | TimeoutException | InterruptedException e) {
            throw new EnvironmentException("Unable to install k3d environment", e);
        }
    }

    @Override
    public void uninstall() throws EnvironmentException {
        logger.info("Uninstalling...");
        if (k3dBinaryPath == null)
            throw new EnvironmentException("Unable to uninstall k3d environment. No k3d binary found. Did you install the k3d environment?");

        try {
            ProcessResult result = new ProcessExecutor().command(k3dBinaryPath.toString(), "cluster", "delete", "mycluster")//
                    .redirectOutput(Slf4jStream.of(getClass()).asInfo())//
                    .redirectError(Slf4jStream.of(getClass()).asError())//
                    .execute();
            if (result.getExitValue() != 0) throw new EnvironmentException("Unable to uninstall k3d environment");

        } catch (IOException | TimeoutException | InterruptedException e) {
            throw new EnvironmentException("Unable to uninstall k3d environment", e);
        }
    }
}
