package com.gigaspaces.demo.rest.server;

import org.openspaces.admin.Admin;
import org.openspaces.admin.machine.Machine;
import org.openspaces.admin.rest.CustomManagerResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@CustomManagerResource
@Path("/demo")
public class BasicPluggableOperationTest {
    @Context
    Admin admin;

    @GET
    @Path("/report")
    public String report(@QueryParam("hostname") String hostname) {
        Machine machine = admin.getMachines().getMachineByHostName(hostname);
        return "Custom report: host=" + hostname +
                ", containers=" + machine.getGridServiceContainers() +
                ", PU instances=" + machine.getProcessingUnitInstances();
    }
}