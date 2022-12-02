(ns day2-rock-paper-scissors.core
  (:gen-class)
  (:require [clojure.string :as str]))

(def options
  {
   "A" 1
   "B" 2
   "C" 3
   "X" 1
   "Y" 2
   "Z" 3
   }
  )

(def options-v2
  {
   "A" 1
   "B" 2
   "C" 3
   "X" :loose
   "Y" :draw
   "Z" :win
   }
  )

(defn get-score [p1 p2]
  (+ p2 (cond
          (and (= p1 1) (= p2 3)) 0
          (and (= p1 3) (= p2 1)) 6
          (< p1 p2) 6
          (= p1 p2) 3
          (> p1 p2) 0
          )
     )
  )

(defn play
  [input]
  (reduce +
          (map
            #(reduce get-score
                     (map options (str/split % #" "))
                     )
            (str/split input #"\n")
            )
          )
  )

(defn choose-right-option [[p1 p2]]
  [p1 (condp = p2
        :loose (condp = p1
                 1 3
                 2 1
                 3 2)
        :draw p1
        :win (condp = p1
               1 2
               2 3
               3 1)
        )
   ]
  )

(defn play-v2
  [input]
  (reduce +
          (map
            #(reduce get-score
                     (choose-right-option (map options-v2 (str/split % #" ")))
                     )
            (str/split input #"\n")
            )
          )
  )

(defn -main [& args]
  (let [in (slurp *in*)]
    (println (play in))
    (println (play-v2 in))
    )
  )
