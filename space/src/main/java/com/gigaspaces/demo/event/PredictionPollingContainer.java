package com.gigaspaces.demo.event;

import com.gigaspaces.demo.common.Prediction;

import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;
import org.openspaces.events.polling.ReceiveHandler;
import org.openspaces.events.polling.receive.MultiTakeReceiveOperationHandler;
import org.openspaces.events.polling.receive.ReceiveOperationHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@EventDriven
@Polling
public class PredictionPollingContainer {

    protected Logger log = Logger.getLogger(this.getClass().getName());


    @ReceiveHandler
    ReceiveOperationHandler receiveHandler() {
        MultiTakeReceiveOperationHandler receiveHandler = new MultiTakeReceiveOperationHandler();
        receiveHandler.setMaxEntries(100);
        return receiveHandler;
    }

    @EventTemplate
    Prediction selector() {
        Prediction template = new Prediction();
        template.setProcessed(false);
        return template;
    }

    @SpaceDataEvent
    public Prediction eventListener(Prediction event) {
        log.log(Level.INFO, "Processing Prediction id: " + event.getId() + ", label: " + event.getLabel() );
        event.setProcessed(Boolean.TRUE);
        return event;
    }
}
