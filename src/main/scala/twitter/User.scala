package twitter

case class User(id: String,
                name: String,
                screen_name: String,
                location: String,
                nb_friends: Int,
                nb_followers: Int,
                nb_statuses: Boolean)

