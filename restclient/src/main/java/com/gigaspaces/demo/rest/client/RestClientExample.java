package com.gigaspaces.demo.rest.client;


import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.gigaspaces.demo.json.MlInstanceDeserializer;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vectors;
import org.insightedge.spark.mllib.MLInstance;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class RestClientExample {

    private MLInstance fromJson(String json) {
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
    private String processResponse(String url, String path) {
        Client client = ClientBuilder.newClient();

        Response response = client.target(url)
                .path(path)
                .queryParam("id", "KMeansModel")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        String json = response.readEntity(String.class);
        System.out.println("Output from Server .... \n");
        System.out.println(response.getStatus());
        System.out.println(json);
        return json;
    }

    private void doPrediction(MLInstance mlInstance) {
        KMeansModel model = (KMeansModel) mlInstance.instance();
        System.out.println("Do prediction...");
        int prediction = model.predict(Vectors.dense(11.5775, 61.5, 14, 73.2, 19.59));
        System.out.println("Prediction is: " + prediction);
    }
    public static void main(String[] args) {
        try {

            if( args.length < 2 ) {
                System.out.println("Usage: java -jar jarfile <url> <path>");
                System.exit(-1);
            }

            String url = args[0];
            String path = args[1];

            RestClientExample example = new RestClientExample();

            String json = example.processResponse(url, path);
            MLInstance mlInstance = example.fromJson(json);
            example.doPrediction(mlInstance);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
