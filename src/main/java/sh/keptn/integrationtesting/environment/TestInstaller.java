package sh.keptn.integrationtesting.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.stream.slf4j.Slf4jStream;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.concurrent.TimeoutException;

public class TestInstaller implements Installer {

    private Logger logger = LoggerFactory.getLogger(TestInstaller.class);

    @Override
    public void install() throws EnvironmentException {
        logger.info("Installing...");
    }

    @Override
    public void uninstall() throws EnvironmentException {
        logger.info("Uninstalling...");
    }
}
