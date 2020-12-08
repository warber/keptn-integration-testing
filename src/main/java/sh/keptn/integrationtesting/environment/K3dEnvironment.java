package sh.keptn.integrationtesting.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author warber
 **/
public class K3dEnvironment extends KeptnEnvironment {


    private Logger logger = LoggerFactory.getLogger(K3dEnvironment.class);

    protected K3dEnvironment(Installer installer) {
        super(installer);
    }


    public static K3dEnvironment create() {
        return new K3dEnvironment(new K3dLocalInstaller(new FileDownloader()));
    }

    @Override
    public void init() {
        logger.info("Initializing k3d environment");
        try {
            installer.install();
        } catch (EnvironmentException ee) {
            logger.error("Unable to initialize k3d environment", ee);
        }

    }

    @Override
    public void tearDown() {
        logger.info("Tear down k3d environment");
        try {
            installer.uninstall();
        } catch (EnvironmentException ee) {
            logger.error("Unable to tear down k3d environment", ee);
        }
    }

}
