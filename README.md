This is stock prediction demo that includes:

1. Stock prediction ML notebook with Kafka consumer
2. Kafka producer which writes sample data
3. Space w/ a Polling Container (low-level event processing)
4. Rest service that returns an ML model
5. Rest client that reads an ML model

### Pre-requisites
1. Java 8 or 11
2. Maven
3. Gigaspaces install zip extracted
4. Kafka
5. The following ports need to be opened:

| Port | Description | Example |
|------|-------------|---------|
| 8099 | Gigaspaces legacy Web UI | http://<hostname>:8099 |
| 8090 | Gigaspaces Ops Manager and REST Manager | http://<hostname>:8090 and http://<hostname>:8090/v2 |
| 9090 | Gigaspaces Zeppelin integration | http://<hostname>:9090 |
| 8091 | Demo of war file with rest service | Described below |

### Setup and running the demo
1. Clone this repository
2. Run mvn package
3. Start Gigaspaces with bin/startAgent.sh
4. Start Kafka. In bin directory, source setKafkaHome.sh, bin/kafka-start.sh
5. Deploy the space and polling container using bin/deploySpace.sh
6. Deploy REST server using bin/deployRestService.sh
7. Write some items to Kafka. Run feeder/target/feeder.sh
8. In a browser go to Zeppelin. For example, http://localhost:9090
Load the notebook in notebooks/stock_prediction_using_technical_indicators.json
Run each paragraph.
9. In a browser go to REST service. For example, http://localhost:8091/restserver/rest/mlinstance?id=KMeansModel
10. Run the REST client with restclient/target/bin/run.sh