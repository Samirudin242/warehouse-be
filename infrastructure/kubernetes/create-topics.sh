#local-confluent-kafka-cp-zookeeper-headless
kafka-topics --zookeeper $1:2181 --topic warehouse-reporting-request --delete --if-exists
kafka-topics --zookeeper $1:2181 --topic warehouse-reporting-response --delete --if-exists

kafka-topics --zookeeper $1:2181 --topic warehouse-reporting-request --create --partitions 3 --replication-factor 1 --if-not-exists
kafka-topics --zookeeper $1:2181 --topic warehouse-reporting-response --create --partitions 3 --replication-factor 1 --if-not-exists
