(ns cav.cljspark
  (:import [org.apache.spark.api.java JavaSparkContext]
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

(declare ^:dynamic ^JavaSparkContext *sc*)

(defmacro with-context
  "Make context implicit for easier usage."
  [sc & body]
  `(binding [*sc* ~sc]
     ~@body))

(defn parallelize
  "sc.parallelize"
  ([coll] (parallelize coll 1))
  ([coll slices] (.parallelize *sc* coll slices)))

(defn text-file
  "sc.textFile"
  ([path] (text-file path 1))
  ([path min-partitions]
   (.textFile *sc* path min-partitions)))

(defn broadcast
  "sc.broadcast"
  [x]
  (.broadcast *sc* x))


(comment


  )
