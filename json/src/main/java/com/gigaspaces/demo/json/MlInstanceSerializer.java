package com.gigaspaces.demo.json;

import org.insightedge.spark.mllib.MLInstance;
import org.apache.spark.mllib.clustering.KMeansModel;




import com.fasterxml.jackson.core.JsonGenerator;
        import com.fasterxml.jackson.databind.SerializerProvider;
        import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MlInstanceSerializer extends StdSerializer<MLInstance> {

    protected MlInstanceSerializer(Class<MLInstance> t) {
        super(t);
    }

    public void serialize(MLInstance mlInstance, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", mlInstance.getId());
        Object obj = mlInstance.getInstance();
        if( obj instanceof KMeansModel ) {
            // Prepare the byte array to send:
            byte[] yourBytes;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(bos);
                out.writeObject((KMeansModel) obj);
                out.flush();
                yourBytes = bos.toByteArray();
            } finally {
                try {
                    bos.close();
                } catch (IOException ex) {
                    // ignore close exception
                }
            }
            //jsonGenerator.writeArrayFieldStart("instance");
            jsonGenerator.writeBinaryField("instance", yourBytes);
            //jsonGenerator.writeEndArray();
        }
        jsonGenerator.writeEndObject();
    }
}