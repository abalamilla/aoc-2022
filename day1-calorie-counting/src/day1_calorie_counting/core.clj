(ns day1-calorie-counting.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn calories-count [accum next-value]
  (let [
        current-value (or (last accum) "0")
        int-current-value (Integer/parseInt current-value)
        is-next-value-empty (empty? next-value)
        int-next-value (if is-next-value-empty 0 (Integer/parseInt next-value))
        sum (+ int-current-value int-next-value)
        str-sum (str sum)
        is-accum-empty (empty? accum)
        ]
    (if is-next-value-empty
      (conj accum "0")
      (if is-accum-empty
        [str-sum]
        (conj (pop accum) str-sum))
      ))
  )

(defn which-elf-to-ask
  [input]
  (let [
        input-array (str/split input #"\n")
        calories-count (reduce calories-count [] input-array)
        int-calories-count (map #(Integer/parseInt %) calories-count)
        max (reduce max int-calories-count)
        elf (+ 1 (.indexOf int-calories-count max))
        ]
    elf)
  )
