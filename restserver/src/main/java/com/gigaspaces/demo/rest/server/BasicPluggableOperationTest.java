package com.gigaspaces.demo.rest.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gigaspaces.demo.json.MlInstanceSerializer;
import com.j_spaces.core.client.SQLQuery;
import org.insightedge.spark.mllib.MLInstance;

import org.openspaces.admin.Admin;
import org.openspaces.admin.machine.Machine;
import org.openspaces.admin.rest.CustomManagerResource;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

@CustomManagerResource
@Path("/demo")
public class BasicPluggableOperationTest {

    @Context
    Admin admin;

    GigaSpace gigaSpace;

    private void init() {
        SpaceProxyConfigurer configurer = new SpaceProxyConfigurer("demo");
        gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();
    }

    @GET
    @Path("/mlinstance")
    public String mlinstance(@QueryParam("id") String id) {

        if( gigaSpace == null ) {
            init();
        }

        SQLQuery<MLInstance> query = new SQLQuery<MLInstance>(MLInstance.class, "id = ?");
        query.setParameter(1, id);
        MLInstance readItem = gigaSpace.read(query);

        String json = toJson(readItem);

        return json;
    }
    @GET
    @Path("/report")
    public String report(@QueryParam("hostname") String hostname) {
        Machine machine = admin.getMachines().getMachineByHostName(hostname);
        return "Custom report: host=" + hostname +
                ", containers=" + machine.getGridServiceContainers() +
                ", PU instances=" + machine.getProcessingUnitInstances();
    }
    private String toJson(MLInstance mlInstance) {
        String json = null;
        MlInstanceSerializer serializer = new MlInstanceSerializer(org.insightedge.spark.mllib.MLInstance.class);
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module =
                new SimpleModule("MlInstanceSerializer", new Version(2, 11, 2, null, null, null));
        module.addSerializer(MLInstance.class, serializer);

        objectMapper.registerModule(module);

        try {
            json = objectMapper.writeValueAsString(mlInstance);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        return json;
    }
}