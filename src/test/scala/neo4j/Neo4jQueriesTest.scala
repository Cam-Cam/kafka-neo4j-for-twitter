package neo4j

import org.neo4j.driver.v1._
import org.scalatest.FunSuite
import twitter.Tweet

import scala.collection.JavaConverters._


class Neo4jQueriesTest extends FunSuite {

  val uri: String = "bolt://localhost:7687"
  val user: String = "neo4j"
  val password: String = "231287"

  val driver: Driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password))



  def runCypherQuery(query:String) : List[Record] = {
    val session: Session = driver.session()
    session.run(query).list().asScala.toList
  }


  test("Import 1 tweet"){

    val tweet1: Tweet = Tweet("1","20180534","blablabla","9","8",true)
    val tweets: List[Tweet] = List(tweet1)

    val session: Session = driver.session()

    tweets.foreach(t=>session.run(Neo4jQueries.writeTweet(t)).list().asScala.toList)

  }


  test("Test idempotence"){

    val tweet1: Tweet = Tweet("ID1","20180534","blablabla","9","8",true)

    val tweets: List[Tweet] = List(tweet1)

    val session: Session = driver.session()

    tweets.foreach(t=>{
      Neo4jQueries.deleteConstraints
      Neo4jQueries.createConstraint("Tweet","id")
      session.run(Neo4jQueries.writeTweet(t)).list().asScala.toList
      session.run(Neo4jQueries.writeTweet(t)).list().asScala.toList
    })


  }

  test("Import 3 tweets twice"){}

}
