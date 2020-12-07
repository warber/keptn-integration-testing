package sh.keptn.integrationtesting.environment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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