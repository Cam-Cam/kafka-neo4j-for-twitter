name := "kafka-neo4j"

version := "0.1"

scalaVersion := "2.12.6"

resolvers += "Confluent Repo" at "http://packages.confluent.io/maven"

libraryDependencies += "org.apache.kafka" %% "kafka" % "1.0.1"

libraryDependencies += "io.confluent" % "kafka-avro-serializer" % "4.0.0"

libraryDependencies +="org.apache.avro"  %  "avro"  %  "1.7.7"

libraryDependencies += "org.neo4j.driver" % "neo4j-java-driver" % "1.4.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.5.0"

libraryDependencies += "com.github.scopt" %% "scopt" % "3.7.0"

libraryDependencies += "com.twitter" %% "bijection-core" % "0.9.6"

libraryDependencies += "org.apache.kafka" % "connect-api" % "1.1.0"

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "3.0.3"

libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "3.0.3"