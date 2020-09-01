package com.gigaspaces.demo.rest.server;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j_spaces.core.client.SQLQuery;
import org.apache.spark.mllib.clustering.KMeansModel;

import org.apache.spark.mllib.linalg.Vectors;
import org.insightedge.spark.mllib.MLInstance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.SpaceProxyConfigurer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JsonExample {

    private GigaSpace gigaSpace;


    public void setGigaSpace(GigaSpace gigaSpace) { this.gigaSpace = gigaSpace; }


    public String toJson(MLInstance mlInstance) {
        String json = null;
        MlInstanceSerializer serializer = new MlInstanceSerializer(org.insightedge.spark.mllib.MLInstance.class);
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module =
                new SimpleModule("MlInstanceSerializer", new Version(2, 11, 2, null, null, null));
        module.addSerializer(MLInstance.class, serializer);

        objectMapper.registerModule(module);

        try {
            json = objectMapper.writeValueAsString(mlInstance);
            objectMapper.writeValue(
                    new FileOutputStream("data/output-2.json"), mlInstance);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        return json;
    }
    public MLInstance fromJson(String json) {
        MLInstance mlInstanceFromJson = null;
        SimpleModule module =
                new SimpleModule("MlInstanceDeserializer", new Version(2, 11, 2, null, null, null));
        module.addDeserializer(MLInstance.class, new MlInstanceDeserializer(MLInstance.class));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);

        try {
            mlInstanceFromJson = mapper.readValue(json, MLInstance.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mlInstanceFromJson;
    }

    public void compareInstances(MLInstance mlInstanceFromJson, MLInstance mlInstanceFromGrid) {
        System.out.println("mlInstanceFromJson.id: " + mlInstanceFromJson.getId());
        System.out.println("mlInstnaceFromJson.instance: " + mlInstanceFromJson.getInstance());
        System.out.println("mlInstance equal to mlInstance generated from JSON? " + mlInstanceFromGrid.equals(mlInstanceFromJson));

    }
    public void predict(MLInstance mlInstance) {
        KMeansModel kMeansModel = (KMeansModel) mlInstance.getInstance();

        int modelPrediction = kMeansModel.predict(Vectors.dense(11.5775, 61.5, 14, 73.2, 19.59));

        System.out.println("modelPrediction is: " + modelPrediction);
    }
    public String toJson_v0(MLInstance mlInstance) {
        ObjectMapper objectMapper = new ObjectMapper();

        org.insightedge.spark.mllib.MLInstance data = mlInstance;

        String json = null;
        try {
            json = objectMapper.writeValueAsString(data);
            objectMapper.writeValue(
                    new FileOutputStream("data/output-2.json"), data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
    public MLInstance read() {
        SQLQuery<MLInstance> query = new SQLQuery<MLInstance>(MLInstance.class, "");
        MLInstance readItem = gigaSpace.read(query);
        return readItem;
    }
    public static void main(String[] args) {

        JsonExample example = new JsonExample();
        example.init();
        MLInstance mlInstanceFromGrid = example.read();
        String json = example.toJson(mlInstanceFromGrid);
        MLInstance instanceFromJson = example.fromJson(json);
        example.compareInstances(instanceFromJson, mlInstanceFromGrid);
        example.predict(instanceFromJson);
        example.toJson(instanceFromJson);
    }
}
