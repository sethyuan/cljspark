(ns cav.cljspark.rdd
  (:refer-clojure :exclude [map mapcat filter count reduce])
  (:require [clojure.core :as core])
  (:import [org.apache.spark.api.java JavaRDD]
           [org.apache.spark.api.java.function
            Function Function2 FlatMapFunction FlatMapFunction2]))

(def ^:private func
  (memoize (fn [f] (reify Function (call [this x] (f x))))))

(def ^:private func2
  (memoize (fn [f] (reify Function2 (call [this x y] (f x y))))))

(def ^:private flat-func
  (memoize (fn [f] (reify FlatMapFunction (call [this x] (f x))))))

(def ^:private flat-func2
  (memoize (fn [f] (reify FlatMapFunction2 (call [this x y] (f x y))))))

(defn map
  "rdd.map"
  [f ^JavaRDD rdd]
  (.map rdd (func f)))

(defn mapcat
  "rdd.flatMap"
  [f ^JavaRDD rdd]
  (.flatMap rdd (flat-func f)))

(defn filter
  "rdd.filter"
  [pred ^JavaRDD rdd]
  (.filter rdd (func #(if (pred %) true false))))

(defn count
  "rdd.count"
  [^JavaRDD rdd]
  (.count rdd))

(defn reduce
  "rdd.reduce"
  [f ^JavaRDD rdd]
  (.reduce rdd (func2 f)))

(defn collect
  "rdd.collect"
  [^JavaRDD rdd]
  (.collect rdd))
