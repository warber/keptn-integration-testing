package sh.keptn.integrationtesting.environment;

import org.junit.jupiter.api.Test;

public class TestInstallerTest {

    @Test
    public void test() {
        TestInstaller testInstaller = new TestInstaller();
        testInstaller.install();
    }
}
