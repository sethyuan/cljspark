(defproject cav/cljspark "0.1.0-SNAPSHOT"
  :description "Clojure wrapper for Spark."
  :url "https://github.com/sethyuan/cljspark"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:provided {:dependencies [[org.apache.spark/spark-core_2.10 "1.0.0"]]}
             :test {:aot :all}})
