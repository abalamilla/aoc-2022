(ns day10-cathode-ray-tube.core
  (:gen-class)
  (:require [clojure.string :as str]))

(def instructions
  {
   "noop" 1
   "addx" 2
   })

(defn process [input]
  (reduce
    (fn [[strength sum cycles] [op value]]
      (let [
            mod-cycle (mod cycles 40)
            current-cycle (+ cycles
                             (cond
                               (and (= mod-cycle 18) (= op 2)) op
                               (and (= mod-cycle 19) (= op 2)) (dec op)
                               (= mod-cycle 19) op
                               :else (- cycles)
                               )
                             )
            current-strength (* current-cycle sum)
            ]
        [(+ strength current-strength)
         (+ sum value)
         (+ cycles op)
         op
         value])
      )
    [0 1 0]
    (mapv
      #(apply
         (fn [instruction & value] [(instructions instruction) (Integer/parseInt (nth value 0 "0"))])
         (str/split % #" "))
      (str/split-lines input)
      )
    )
  )

(defn signal-strength
  [input]
  (first
    (process input)
    )
  )

(defn -main [& args]
  (let [in (slurp *in*)]
    (println "signal strength" (signal-strength in))))
