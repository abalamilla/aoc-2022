(ns day8-treetop-tree-house.core
  (:gen-class)
  (:require [clojure.string :as str])
  )

(defn parseInt [input]
  (Integer/parseInt input)
  )

(defn process [matrix]
  (let [
        transposed (apply mapv vector matrix)
        ]
    (reduce-kv                                              ; rows
      (fn [accum row r-value]
        (reduce-kv
          (fn [accum col c-value]                           ; cols
            (cond
              (or
                (= (count (filter #(>= % c-value) (take col r-value))) 0) ; left
                (= (count (filter #(>= % c-value) (drop (inc col) r-value))) 0) ; right
                (= (count (filter #(>= % c-value) (take row (transposed col)))) 0) ; bottom
                (= (count (filter #(>= % c-value) (drop (inc row) (transposed col)))) 0) ; top
                )
              (inc accum)
              :else accum
              )
            )
          accum
          r-value
          )
        )
      0
      matrix
      )
    )
  )

(defn take-until [pred stop col]
  (reduce
    #(cond
       (stop %2) (->>
                   %2
                   (conj %)
                   (reduced))
       (pred %2) (conj % %2)
       )
    []
    col
    )
  )

(defn process-v2 [matrix]
  (let [
        transposed (apply mapv vector matrix)
        ]
    (reduce-kv                                              ; rows
      (fn [accum row r-value]
        (reduce-kv
          (fn [accum col c-value]                           ; cols

            (let [
                  score (*
                          (count (take-until #(< % c-value) #(>= % c-value) (reverse (take col r-value)))) ; left
                          (count (take-until #(< % c-value) #(>= % c-value) (drop (inc col) r-value))) ; right
                          (count (take-until #(< % c-value) #(>= % c-value) (reverse (take row (transposed col))))) ; bottom
                          (count (take-until #(< % c-value) #(>= % c-value) (drop (inc row) (transposed col)))) ; top
                          )
                  ]
              (cond
                (> score accum) score
                :else accum
                )
              )
            )
          accum
          r-value
          )
        )
      0
      matrix
      )
    )
  )

(defn get-visible-trees [input]
  (process
    (mapv
      #(mapv parseInt (str/split % #""))
      (str/split-lines input)
      )
    )
  )

(defn get-scenic-score [input]
  (process-v2
    (mapv
      #(mapv parseInt (str/split % #""))
      (str/split-lines input)
      ))
  )

(defn -main [& args]
  (let [in (slurp *in*)]
    (println "visible trees: " (get-visible-trees in))
    (println "visible trees: " (get-scenic-score in))
    )
  )



