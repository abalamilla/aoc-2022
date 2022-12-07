(ns day6-turning-trouble.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn validate [input]
  (and
    (= (count input) 1)
    (= (first input) 1)
    )
  )

(defn get-marker [input partition-count]
  (+ partition-count
     (str/index-of
       input
       (str/join
         (first
           (filter
             #(validate (set (vals (frequencies %))))
             (partition partition-count 1 input)
             )
           )
         )
       )
     )
  )

(defn -main [& args]
  (let [in (slurp *in*)]
    (println "marker 4" (get-marker in 4))
    (println "marker 14" (get-marker in 14))
    )
  )
