package sh.keptn.integrationtesting;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.keptn.integrationtesting.environment.*;


public class Testing extends KeptnTest {


    @Test
    public void test1() {
        System.out.println("#### running test 1");
    }

    @Test
    public void test2() {
        System.out.println("#### running test 2");

    }

    @Override
    KeptnEnvironment getEnvironment() {
        return new TestEnvironment(new TestInstaller(), "my-custom-env");
    }


}