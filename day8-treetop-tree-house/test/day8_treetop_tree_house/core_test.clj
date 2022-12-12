(ns day8-treetop-tree-house.core-test
  (:require [clojure.test :refer :all]
            [day8-treetop-tree-house.core :refer :all]))

(deftest a-test
  (testing "visible trees"
    (let [
          expected 21
          input (slurp "resources/test.txt")
          actual (get-visible-trees input)
          ]
      (is (= actual expected))
      )
    )

  (testing "scenic score"
    (let [
          expected 8
          input (slurp "resources/test.txt")
          actual (get-scenic-score input)
          ]
      (is (= actual expected))
      )
    )
  )
