package twitter

import org.apache.avro.generic.GenericRecord

case class Tweet(id: String,
                 created_at: String,
                 text: String,
                 nb_retweets: String,
                 nb_favorites: String,
                 is_retweet: Boolean) {



}


object Tweet {

  def apply(id: String,
            created_at: String,
            text: String,
            nb_retweets: String,
            nb_favorites: String,
            is_retweet: Boolean)=
    new Tweet(id=id,created_at=created_at,text=text,nb_retweets=nb_retweets,nb_favorites=nb_favorites,is_retweet=is_retweet)

//  def ingestTweet(record: GenericRecord): Tweet = {
//
//    Tweet(record.get("id").toString,
//      record.get("created_at").toString,
//      record.get("text").toString,
//      record.get("nb_retweets").toString,
//      record.get("nb_favorites").toString,
//      record.get("is_retweet").toString)
//
//  }


  }




