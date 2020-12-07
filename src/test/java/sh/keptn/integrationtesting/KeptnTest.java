package sh.keptn.integrationtesting;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.commons.util.Preconditions;
import sh.keptn.integrationtesting.environment.KeptnEnvironment;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class KeptnTest {


    private KeptnEnvironment environment;


    @BeforeAll
    public void setup() {
        this.environment = getEnvironment();
        environment.init();


    }

    @AfterAll
    public void teardown() {
        Preconditions.notNull(environment, "Environment must be not null");
        environment.tearDown();
    }


    abstract KeptnEnvironment getEnvironment();

}
