(ns day5-supply-stacks.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn parseInt [str]
  (Integer/parseInt str)
  )

(defn decode [[crates instructions]]
  (let [
        arr-crates (map
                     #(into [] %)
                     (filter
                       not-empty
                       (map
                         #(filter (fn [n] (Character/isLetter n)) %)
                         (apply mapv vector crates)
                         )
                       )
                     )
        arr-instructions (map
                           #(map
                              parseInt
                              (re-seq
                                #"\d+"
                                %
                                ))
                           instructions
                           )
        ]
    [arr-crates arr-instructions]
    )
  )

(defn move [crates [count from to]]
  (let
    [
     arr-crates (into [] crates)
     idx-from (- from 1)
     idx-to (- to 1)
     move-from-to (assoc
                    arr-crates
                    idx-to
                    (into
                      []
                      (concat
                        (reverse
                          (take count (arr-crates idx-from))
                          )
                        (arr-crates idx-to)
                        )
                      )
                    )
     ]
    (assoc
      move-from-to
      idx-from
      (drop
        count
        (arr-crates idx-from)
        )
      )
    )
  )

(defn move-v2 [crates [count from to]]
  (let
    [
     arr-crates (into [] crates)
     idx-from (- from 1)
     idx-to (- to 1)
     move-from-to (assoc
                    arr-crates
                    idx-to
                    (into
                      []
                      (concat
                        (take count (arr-crates idx-from))
                        (arr-crates idx-to)
                        )
                      )
                    )
     ]
    (assoc
      move-from-to
      idx-from
      (drop
        count
        (arr-crates idx-from)
        )
      )
    )
  )

(defn apply-instructions [[crates instructions] fn]
  (reduce
    fn
    crates
    instructions
    )
  )

(defn get-top-stacks [input fn]
  (str/join
    (map
      first
      (apply-instructions
        (decode
          (map
            #(str/split % #"\n")
            (str/split input #"\n\n")
            )
          )
        fn
        )
      )
    )
  )

(defn -main [& args]
  (let [in (slurp *in*)]
    (println "top crates" (get-top-stacks in move))
    (println "top crates" (get-top-stacks in move-v2))
    )
  )
