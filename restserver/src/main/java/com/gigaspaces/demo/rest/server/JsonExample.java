package com.gigaspaces.demo.rest.server;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.j_spaces.core.client.SQLQuery;
import org.insightedge.spark.mllib.MLInstance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

public class JsonExample {

    private GigaSpace gigaSpace;
    private MLInstance mlInstance;

    public void setGigaSpace(GigaSpace gigaSpace) { this.gigaSpace = gigaSpace; }

    public void setMlInstance(MLInstance mlInstance) {
        this.mlInstance = mlInstance;
    }
    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        org.insightedge.spark.mllib.MLInstance data = mlInstance;

        String json = null;
        try {
            json = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        return(json);
    }
    public void init() {
        SpaceProxyConfigurer configurer = new SpaceProxyConfigurer("demo");
        gigaSpace = new GigaSpaceConfigurer(configurer).gigaSpace();

        setGigaSpace(gigaSpace);
    }
    public void read() {
        SQLQuery<MLInstance> query = new SQLQuery<MLInstance>(MLInstance.class, "");
        MLInstance readItem = gigaSpace.read(query);
        setMlInstance(readItem);
    }
    public static void main(String[] args) {

        JsonExample example = new JsonExample();
        example.init();

    }
}
