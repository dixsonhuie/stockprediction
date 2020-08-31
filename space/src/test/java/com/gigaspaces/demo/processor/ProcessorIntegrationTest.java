package com.gigaspaces.demo.processor;

import com.gigaspaces.demo.common.Prediction;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.openspaces.core.GigaSpace;
import shapeless.ops.nat;


/**
 * Integration test for the Processor. Uses similar xml definition file
 * (ProcessorIntegrationTest-context.xml) to the actual pu.xml. Writs an unprocessed Data to the
 * Space, and verifies that it has been processed by taking a processed one from the space.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProcessorIntegrationTest {

    @Autowired
    GigaSpace gigaSpace;

    @Before
    @After
    public void clearSpace() {
        gigaSpace.clear(null);
    }

    @Test
    public void verifyProcessing() throws Exception {
        // write the data to be processed to the Space
        Prediction data = new Prediction(String.valueOf(1), "test", 1);
        gigaSpace.write(data);

        // create a template of the processed data (processed)
        Prediction template = new Prediction();
        template.setLabel("test");
        template.setProcessed(true);

        // wait for the result
        Prediction result = gigaSpace.take(template, 500);
        // verify it
        assertNotNull("No data object was processed", result);
        assertEquals("Processed Flag is false, data was not processed", true, result.isProcessed());
    }
}
