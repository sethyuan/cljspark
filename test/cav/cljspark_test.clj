(ns cav.cljspark-test
  (:require [cav.cljspark :as spark]
            [cav.cljspark.rdd :as rdd]
            [clojure.test :refer :all]))

(deftest spark-context-test
  (testing "with context"
    (spark/with-context (spark/context :app-name "Hello World" :master "local[2]")
      (is (= (->> (spark/parallelize (range 1 11))
                  (rdd/count))
             10))))
  (testing "simple map reduce"
    (spark/with-context (spark/context :app-name "Simple map" :master "local[2]")
      (is (= (->> (spark/parallelize (range 1 11))
                  (rdd/map (partial * 2))
                  (rdd/reduce +))
             110))))
;; (def sc (context :app-name "Hello App" :master "local[2]"))
;;
;; (with-context sc
;;   (let [readme (text-file "README.md" 2)]
;;     (-> readme
;;         (mapcat #(string/split % " "))
;;         (filter #{"Spark"})
;;         (count)
;;         (println))))
  )
