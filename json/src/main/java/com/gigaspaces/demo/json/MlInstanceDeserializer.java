package com.gigaspaces.demo.json;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.insightedge.spark.mllib.MLInstance;
import org.apache.spark.mllib.clustering.KMeansModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class MlInstanceDeserializer extends StdDeserializer<MLInstance> {

    public MlInstanceDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public MLInstance deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        MLInstance mlInstance = new MLInstance();
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();
                System.out.println(fieldName);

                jsonToken = parser.nextToken();

                if ("id".equals(fieldName)) {
                    mlInstance.setId(parser.getValueAsString());
                } else if ("instance".equals(fieldName)) {
                    byte[] yourBytes = parser.getBinaryValue();
                    KMeansModel o = null;
                    ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
                    ObjectInput in = null;
                    try {
                        in = new ObjectInputStream(bis);
                        o = (KMeansModel) in.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (in != null) {
                                in.close();
                            }
                        } catch (IOException ex) {
                            // ignore close exception
                        }
                    }
                    mlInstance.setInstance(o);
                }
            }
        }
        return mlInstance;
    }
}
