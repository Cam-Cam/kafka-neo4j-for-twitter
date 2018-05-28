package twitter

import org.apache.avro.generic.GenericRecord

case class Tweet(
                  id: String,
                  created_at: String,
                  text: String,
//nb_retweets: String,
// nb_favorites: String,
  is_retweet: Boolean, user: User) {


}


object Tweet {

  def apply(id: String, created_at: String, text: String, //nb_retweets: String,
            //nb_favorites: String,
            is_retweet: Boolean, user: User) = new Tweet(id = id, created_at = created_at, text = text, //nb_retweets=nb_retweets,
    //nb_favorites=nb_favorites,
    is_retweet = is_retweet, user = user)


}




