package sh.keptn.integrationtesting.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEnvironment extends KeptnEnvironment {


    private final Logger logger = LoggerFactory.getLogger(TestEnvironment.class);

    private final String name;

    public TestEnvironment(Installer installer, String name) {
        super(installer);
        this.name = name;
    }


    @Override
    public void init() {
        logger.info("Initializing environment < " + name + " >");
        installer.install();
    }

    @Override
    public void tearDown() {
        logger.info("Tear down environment < " + name + " >");
        installer.uninstall();
    }


    @Override
    public KubeClusterConfig getKubeClusterConfig() {
        return null;
    }
}
