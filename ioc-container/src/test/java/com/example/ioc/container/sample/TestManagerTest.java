package com.example.ioc.container.sample;

import com.example.ioc.container.exception.DIGraphException;
import com.example.ioc.container.lib.DIGraph;
import com.example.ioc.container.util.Utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestManagerTest {
    private DIGraph injector;

    @Before
    public void setUp() {
        injector = DIGraph.getContainer();

    }

    public void forgetToInstallDependency_throwsForgotException() {
        // ARRANGE
        try {
            injector.generateGraph();
            TestService testService = injector.inject(TestService.class);

            // ACT
            String outcome = testService.sayHello();
        } catch (DIGraphException e) {
            assertEquals(Utils.EXPECTED_FORGOT_MESSAGE, e.getMessage());
        }
    }

    @Test
    public void sayHello_returnsExpectedString() throws DIGraphException {
        // ARRANGE
        injector.installDependency(TestManager.class);
        injector.generateGraph();
        TestService testService = injector.inject(TestService.class);

        // ACT
        String outcome = testService.sayHello();

        assertEquals(Utils.EXPECTED_MESSAGE, outcome);
    }

}