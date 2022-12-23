(ns day11-monkey-in-the-middle.core-test
  (:require [clojure.test :refer :all]
            [day11-monkey-in-the-middle.core :refer :all])
  (:use [clojure.pprint :only [pprint]]))

(deftest a-test
  (testing "monkey business level 20 rounds"
    (let [
          expected 10605
          input (slurp "resources/test.txt")
          actual (get-monkey-business-level input 20 3)
          ]
      (pprint ["🧪" actual])
      (is (= actual expected))))
  )
