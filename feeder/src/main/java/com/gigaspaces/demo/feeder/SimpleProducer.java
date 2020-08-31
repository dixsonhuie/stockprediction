package com.gigaspaces.demo.feeder;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SimpleProducer {

    public static void main(String[] args) throws Exception {

        if (args.length < 2) {
            System.err.println("Usage: java -jar <jar filename> <topic name> <data filename>");
            System.exit(-1);
        }

        String topicName = args[0];
        String dataFileName = args[1];

        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(dataFileName), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        int i = 0;
        for(String s: lines) {
            producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i++), s));

            if( i % 4 == 0) {
                Thread.sleep(20000L);
            }
        }
        producer.close();

    }
}
