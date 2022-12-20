(ns day10-cathode-ray-tube.core-test
  (:require [clojure.test :refer :all]
            [day10-cathode-ray-tube.core :refer :all])
  (:use [clojure.pprint :only (pprint)]))

(deftest a-test
  (testing "signal strength"
    (let [
          expected 13140
          input (slurp "resources/test.txt")
          actual (signal-strength input)
          ]

      (pprint actual)
      (is (= actual expected))))
  )
