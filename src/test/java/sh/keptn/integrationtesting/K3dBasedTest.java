package sh.keptn.integrationtesting;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import sh.keptn.integrationtesting.environment.K3dEnvironment;
import sh.keptn.integrationtesting.environment.KeptnEnvironment;

/**
 * @author warber
 **/
@Ignore
public class K3dBasedTest extends KeptnTest {

    @Test
    public void test1() {
        System.out.println("Test1");
    }

    @Override
    KeptnEnvironment getEnvironment() {
        return K3dEnvironment.create();
    }

    @Override
    KeptnInstaller.KeptnInstallerBuilder getKeptnInstallationBuilder() {
        return KeptnInstaller.KeptnInstallerBuilder.createContinuousDelivery();
    }
}
