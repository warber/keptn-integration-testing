package sh.keptn.integrationtesting.environment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author warber
 **/
@ExtendWith(MockitoExtension.class)
class K3dEnvironmentTest {


    @Mock
    private Installer installer;

    @Test
    public void testWhenInstallerFails() {

        K3dEnvironment instance = new K3dEnvironment(installer);
        instance.init();
    }


}