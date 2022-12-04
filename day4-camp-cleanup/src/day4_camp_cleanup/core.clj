(ns day4-camp-cleanup.core
  (:gen-class)
  (:require
    [clojure.set :as set]
    [clojure.string :as str]))

(defn parseInt [str]
  (Integer/parseInt str)
  )

(defn inclusive-range-set [start end]
  (set (range start (inc end)))
  )

(defn get-ranges [pairs]
  (map
    #(reduce
       inclusive-range-set
       (map
         parseInt
         (str/split % #"-")
         )
       )
    pairs
    )
  )

(defn overlap [pairs]
  (apply
    #(or (set/subset? %1 %2) (set/superset? %1 %2))
    (get-ranges pairs)
    )
  )

(defn intersections [pairs]
  (apply
    set/intersection
    (get-ranges pairs)
    )
  )

(defn count-overlaps [input]
  (count
    (filter
      true?
      (map
        overlap
        (map
          #(str/split % #",")
          (str/split input #"\n")
          )
        )
      )
    )
  )

(defn count-intersections [input]
  (count
    (filter
      not-empty
      (map
        intersections
        (map
          #(str/split % #",")
          (str/split input #"\n")
          )
        )
      )
    )
  )

(defn -main [& args]
  (let [in (slurp *in*)]
    (println "overlaps" (count-overlaps in))
    (println "intersections" (count-intersections in))
    )
  )
