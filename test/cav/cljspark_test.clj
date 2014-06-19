(ns cav.cljspark-test
  (:require [cav.cljspark :as spark]
            [clojure.test :refer :all]))

(deftest spark-context-test
  (testing "Context creation."
    (is (spark/spark-context :app-name "Simple App"
                             :master "local[2]"))))
