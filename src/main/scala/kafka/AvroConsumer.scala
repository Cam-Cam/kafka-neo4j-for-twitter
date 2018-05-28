package kafka

import java.util
import java.util.Properties

import worker.Worker
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer
import twitter.Tweet

import scala.collection.JavaConverters._

object AvroConsumer extends App {

  val topic = "twitter"
  val props = new Properties()

  //Set up the needed properties
  props.put("bootstrap.servers","localhost:9092")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,classOf[StringDeserializer])
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[KafkaAvroDeserializer])
  props.put("schema.registry.url","http://localhost:8081")
  props.put("group.id","something")


  //Create a new consumer
  val consumer = new KafkaConsumer[String,GenericRecord](props)

  //Create a new topic list
  val myTopicList: util.Collection[String] = List[String](topic).asJavaCollection

  consumer.subscribe(myTopicList)

  while(true){
    val records: ConsumerRecords[String, GenericRecord] = consumer.poll(1000)
    records.forEach(r => {
      println(r.value())
      //Worker.writeTweetInformation(Worker.collectTweetInformation(r))
    })

  }
}
