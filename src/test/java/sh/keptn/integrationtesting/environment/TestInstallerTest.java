package sh.keptn.integrationtesting.environment;

import org.junit.jupiter.api.Test;

/**
 * @author warber
 **/
public class TestInstallerTest {

    @Test
    public void test() {
        TestInstaller testInstaller = new TestInstaller();
        testInstaller.install();
    }
}
