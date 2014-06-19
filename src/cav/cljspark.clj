(ns cav.cljspark
  (:import [org.apache.spark.api.java JavaSparkContext JavaRDD]
           [org.apache.spark SparkConf]))

(defn context-with-conf
  "Create a Spark context with a SparkConf."
  [^SparkConf conf]
  (JavaSparkContext. conf))

(defn context
  "Create a Spark context with specified options."
  ([& {:keys [master app-name spark-home jars env config]}]
   (let [conf (SparkConf.)]
     (if master (.setMaster conf master))
     (if app-name (.setAppName conf app-name))
     (if spark-home (.setSparkHome conf spark-home))
     (if jars (.setJars conf ^"[Ljava.lang.String;" (into-array String jars)))
     (if env (doseq [[k v] env] (.setExecutorEnv conf k v)))
     (if config (doseq [[k v] config] (.set conf k v)))
     (JavaSparkContext. conf))))
