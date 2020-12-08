package sh.keptn.integrationtesting.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author warber
 **/
public class TestInstaller implements Installer {

    private final Logger logger = LoggerFactory.getLogger(TestInstaller.class);

    @Override
    public void install() throws EnvironmentException {
        logger.info("Installing...");
    }

    @Override
    public void uninstall() throws EnvironmentException {
        logger.info("Uninstalling...");
    }
}
