package neo4j


import org.slf4j.LoggerFactory
import twitter.{Tweet,User}

class Neo4jQueries

object Neo4jQueries  {

  val logger = LoggerFactory.getLogger(classOf[Neo4jQueries])

//  logger.info(s"Run query constraintLong")
//  def  constraintLongQuery() = {
//    s"CALL apoc.util.sleep(20000)" // wait for 20 seconds
//  }


  def deleteConstraints = "CALL apoc.schema.assert({},{})"

  def createConstraint(node: String, key: String): String = {
//    logger.info(s"Run create constraint query on ${node} with parameter n.${key}")
    s"CREATE CONSTRAINT ON (n:${node}) ASSERT n.${key} IS UNIQUE"
  }

  def writeTweet(tweet: Tweet): String = {
//    logger.info(s"Merge component ${tweet.id} with" +
//      s"[created_at : ${tweet.created_at}]-" + s"[text : ${tweet.text}]-" + s"[nb_retweets : ${tweet.nb_retweets}]-" + s"[nb_favorites : ${tweet.nb_favorites}]-" + s"[is_retweet : ${tweet.is_retweet}]")

    s"MERGE (t:Tweet {id:'${tweet.id}'})" +
      s"ON CREATE SET t.created_at= '${tweet.created_at}'," +
      s"t.text='${tweet.text}'," +
//      s"t.nb_retweets='${tweet.nb_retweets}'," +
//      s"t.nb_favorites='${tweet.nb_favorites}'," +
      s"t.is_retweet='${tweet.is_retweet}'" +
      s"ON MATCH SET t.created_at= '${tweet.created_at}'," +
      s"t.text='${tweet.text}'," +
//      s"t.nb_retweets='${tweet.nb_retweets}'," +
//      s"t.nb_favorites='${tweet.nb_favorites}'," +
      s"t.is_retweet='${tweet.is_retweet}'"
  }

  def writeUser(user: User): String = {
    //    logger.info(s"Merge component ${tweet.id} with" +
    //      s"[created_at : ${tweet.created_at}]-" + s"[text : ${tweet.text}]-" + s"[nb_retweets : ${tweet.nb_retweets}]-" + s"[nb_favorites : ${tweet.nb_favorites}]-" + s"[is_retweet : ${tweet.is_retweet}]")

    s"MERGE (u:User {id:'${user.id}'})" +
      s"ON CREATE SET u.name= '${user.name}'," +
      s"u.screen_name='${user.screen_name}'," +
      s"u.location='${user.location}'," +
      s"u.nb_friends='${user.nb_friends}'," +
      s"u.nb_followers='${user.nb_followers}'," +
      s"u.nb_statuses='${user.nb_statuses}'" +
      s"ON MATCH SET u.name= '${user.name}'," +
      s"u.screen_name='${user.screen_name}'," +
      s"u.location='${user.location}'," +
      s"u.nb_friends='${user.nb_friends}'," +
      s"u.nb_followers='${user.nb_followers}'," +
      s"u.nb_statuses='${user.nb_statuses}'"
  }



//  }
//
//  def writeUser
//
//  def writeCheck(check_id: String): String = {
//    logger.info(s"Merge Check ${check_id}")
//    s"MERGE(:Check {id:'${check_id}'})"
//  }
//
//  def deleteStateRelation(event: Event) = {
//    logger.info(s"Delete state relation between ${event.component} and ${event.check_state} ")
//    s"MATCH (c1:Component)-[r]-(ch1:Check) WHERE c1.id='${event.component}' AND ch1.id='${event.check_state}' DELETE r"
//  }
//
//  def writeRelation( label1: String,id1: String, label2:String,id2: String, relation: String): String = {
//    logger.info(s"Create state ${relation} relation between ${id1} and ${id2} ")
//    s"MERGE(n1:${label1}{id:'${id1}'}) MERGE(n2:${label2}{id:'${id2}'}) MERGE(n1)-[:${relation}]-(n2)"
//  }
//
//  def UpdateOwnState(component: Component,newState: String): String = {
//    logger.info(s"Update state of ${component.id} to ${newState}")
//    s"MATCH (c:Component)" + s"WHERE c.id='${component.id}'" + s"SET c.own_state = '${newState}'"
//  }
//
//  def deleteAllConstraints()= {
//    logger.info(s"Delete all constraints")
//    "CALL apoc.schema.assert({},{})"
//  }


}
