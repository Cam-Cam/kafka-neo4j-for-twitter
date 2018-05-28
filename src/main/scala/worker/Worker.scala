package worker

import kafka.AvroConsumer
import twitter.{Tweet, User}
import neo4j.Neo4jQueries
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords}
import org.neo4j.driver.v1.{AuthTokens, Driver, GraphDatabase, Session}

case class Worker()


object Worker {


  val uri: String = "bolt://localhost:7687"
  val user: String = "neo4j"
  val password: String = "231287"

  val driver: Driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password))

  def collectTweetInformation(record: ConsumerRecord[String, GenericRecord]): Tweet = {

    Tweet(
      record.value().get("id").toString,
      record.value().get("created_at").toString,
      record.value().get("text").toString.replace("'",""),
      //record.value().get("nb_retweets").toString,
      //record.value().get("nb_favorites").toString,
      record.value().get("is_retweet").toString match {
        case "true" => true
        case "false" => false},
      Worker.collectUserInformation(record.value().get("user").asInstanceOf[GenericRecord]))
  }


  def collectUserInformation(record: GenericRecord): User = {
    User(
      record.get("id").toString,
      record.get("name").toString,
      record.get("screen_name").toString,
      if (locationExists(record)) record.get("location").toString else "",
      record.get("friends_count").toString,
      record.get("followers_count").toString,
      record.get("statuses_count").toString)

  }


  def writeTweetInformation(tweet: Tweet) = {

    val session: Session = driver.session()

    if (tweet.is_retweet) {
      session.run(Neo4jQueries.writeTweet(tweet))
      session.run(Neo4jQueries.writeUser(tweet.user))
    }

  }


  def writeUserInformation(user: User) = {

    val session: Session = driver.session()

    session.run(Neo4jQueries.writeUser(user))

  }


  def locationExists(record: GenericRecord): Boolean = {
    if (record.toString.contains("location:")) true else false
  }

}
