(ns day11-monkey-in-the-middle.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defprotocol monkey-actions
  (throw-item [this item monkey])
  (receive-item [this item])
  (play [this idx monkeys worry-level-divided-by])
  )

(defn roundFloor [in]
  (int (Math/floor in))
  )

(defrecord monkey [items operation divider monkey-true monkey-false processed-items]
  monkey-actions
  (throw-item [this item monkey]
    (receive-item monkey item)
    )
  (receive-item [this item]
    (assoc this :items (update items (count items) (fn [_] item)))
    )
  (play [this idx monkeys worry-level-divisor]
    (let [
          new-monkeys (assoc monkeys idx (assoc this :processed-items (+ (:processed-items this) (count (:items (monkeys idx)))) :items []))
          ]
      (reduce
        (fn [accum item]
          (let [
                worry-level (roundFloor (/ ((:op operation) ((:left operation) item) ((:right operation) item)) worry-level-divisor))
                ]
            (if
              (= 0 (mod worry-level divider))
              (assoc accum monkey-true (throw-item this worry-level (accum monkey-true)))
              (assoc accum monkey-false (throw-item this worry-level (accum monkey-false)))
              )))
        new-monkeys
        (:items (monkeys idx))
        ))
    )
  )

(defn parseInt [input]
  (Integer/parseInt input)
  )

(defn get-instruction [input]
  (last (str/split input #":"))
  )

(defn parse-operation [input]
  (let [operators (zipmap
                    [:left :op :right]
                    (drop
                      1
                      (re-find
                        #"new = (\w+) ([+*]) (\w+)"
                        (str/trim input))))]
    {:left  (fn [in]
              in
              )
     :op    (condp = (:op operators)
              "*" *
              +
              )
     :right (fn [in]
              (let [right (:right operators)]
                (if (= right "old") in (parseInt right))
                )
              )
     }
    )
  )

(defn get-number-value [input]
  (parseInt (first (re-seq #"\d+" input)))
  )

(defn make-monkey [_ items operation divider monkey-true monkey-false]
  (monkey.
    (mapv #(parseInt (str/trim %)) (str/split (get-instruction items) #","))
    (parse-operation (get-instruction operation))
    (get-number-value divider)
    (get-number-value monkey-true)
    (get-number-value monkey-false)
    0)
  )

(defn get-monkey-business-level [input rounds worry-level-divisor]
  (let [
        monkeys (mapv
                  #(apply make-monkey %)
                  (map str/split-lines
                       (str/split input #"\n\n")))
        ]
    (reduce
      #(* (:processed-items %1) (:processed-items %2))
      (take
        2
        (sort-by
          :processed-items
          >
          (reduce
            (fn [accum turn]
              (reduce
                #(play (accum %2) %2 %1 worry-level-divisor)
                accum
                (range (count accum))
                )
              )
            monkeys
            (range rounds)
            )))))
  )

(defn -main [& args]
  (let [in (slurp *in*)]
    (println "monkey business level" (get-monkey-business-level in 20 3))
    )
  )
