(ns day1-calorie-counting.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn calories-sum [accum next-value]
  (let [
        values (str/split next-value #"\n")
        int-values (map #(Integer/parseInt %) values)
        sum (apply + int-values)
        ]
    (conj accum sum)
    )
  )

(defn get-sorted-sum [input]
  (let [
        input-array (str/split input #"\n\n")
        sum (reduce calories-sum [] input-array)
        sorted (sort sum)
        ]
    sorted
    )
  )

(defn max-calories
  [input]
  (let [
        sum (get-sorted-sum input)
        max (last sum)
        ]
    max)
  )

(defn top-three
  [input]
  (let [
        sum (get-sorted-sum input)
        reversed (vec (reverse sum))
        top-three (subvec reversed 0 3)
        sum (apply + top-three)]
    sum)
  )

(defn -main [& args]
  (println args)
  (let [in (slurp *in*)]
    (println (max-calories in))
    (println (top-three in))
    )
  )
