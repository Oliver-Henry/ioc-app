package com.example.ioc.container.sample;

import com.example.ioc.container.exception.DIGraphException;
import com.example.ioc.container.lib.DIGraph;
import com.example.ioc.container.util.Utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DependsOnExternalTest {

    private DIGraph injector;

    @Before
    public void setUp() throws DIGraphException {
        injector = DIGraph.getContainer();

    }

    @Test
    public void doSomethingWithExternalDependency_returnsExpectedMessage() throws DIGraphException {
        // ARRANGE
        injector.installDependency(DependsOnExternal.class);
        injector.installDependency(TestManager.class);
        injector.generateGraph();
        ExternalDependencyService externalDependencyService = injector.inject(ExternalDependencyService.class);

        // ACT
        String outcome = externalDependencyService.doSomethingWithExternalDependency();

        // ASSERT
        assertEquals(Utils.EXPECTED_MESSAGE, outcome);
    }

    @Test
    public void doSomethingWithExternalDependency_throwsMissingMessage() {
        try {
            // ARRANGE
            injector.installDependency(DependsOnExternal.class);
            injector.generateGraph();
            ExternalDependencyService externalDependencyService = injector.inject(ExternalDependencyService.class);

            // ACT
            String outcome = externalDependencyService.doSomethingWithExternalDependency();
        } catch (DIGraphException e) {
            // ASSERT
            assertEquals(Utils.EXPECTED_MISSING_MESSAGE, e.getMessage());
        }
    }
}