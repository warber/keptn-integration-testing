package sh.keptn.integrationtesting;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.commons.util.Preconditions;
import sh.keptn.integrationtesting.environment.KeptnEnvironment;

/**
 * @author warber
 **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class KeptnTest {

    private KeptnEnvironment environment;

    private KeptnInstaller.KeptnInstallerBuilder keptnInstallerBuilder;


    @BeforeAll
    public void setup() {
        environment = getEnvironment();
        keptnInstallerBuilder = getKeptnInstallationBuilder();
        environment.init();
    }

    @AfterAll
    public void teardown() {
        Preconditions.notNull(environment, "Environment must be not null");
        environment.tearDown();
    }

    abstract KeptnEnvironment getEnvironment();

    abstract KeptnInstaller.KeptnInstallerBuilder getKeptnInstallationBuilder();

}
