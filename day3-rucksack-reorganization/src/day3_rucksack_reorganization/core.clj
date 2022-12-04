(ns day3-rucksack-reorganization.core
  (:gen-class)
  (:require
    [clojure.set :as set]
    [clojure.string :as str])
  )

(defn chars-range [from to]
  (map char (range (int from) (inc (int to))))
  )

(def items (str/join (concat (chars-range \a \z) (chars-range \A \Z))))

(defn priorities [rucksacks]
  (apply
    conj
    (apply
      set/intersection
      (map set rucksacks)
      )
    )
  )

(defn get-total-priorities
  [input]
  (reduce +
          (map #(+ 1 (.indexOf items %))
               (map priorities
                    (map
                      #(split-at (/ (count %) 2) %)
                      (map
                        #(re-seq #"\w" %)
                        (str/split input #"\n")
                        )
                      )
                    )
               )
          )
  )

(defn get-total-priorities-v2
  [input]
  (reduce +
          (map #(+ 1 (.indexOf items %))
               (map priorities
                    (partition 3
                               (map
                                 #(re-seq #"\w" %)
                                 (str/split input #"\n")
                                 )
                               )
                    )
               )
          )
  )

(defn -main [& args]
  (let [in (slurp *in*)]
    (println (get-total-priorities in))
    (println (get-total-priorities-v2 in))
    )
  )