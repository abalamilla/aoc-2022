(ns day6-turning-trouble.core-test
  (:require [clojure.test :refer :all]
            [day6-turning-trouble.core :refer :all]))

(deftest a-test
  (testing "start of packet marker 4"
    (let
      [
       expected 5
       ;input (slurp "resources/input.txt")
       input "bvwbjplbgvbhsrlpgdmjqwftvncz"
       actual (get-marker input 4)
       ]
      (is (= actual expected))
      )
    )

  (testing "start of packet marker 14"
       (let
         [
          expected 19
          ;input (slurp "resources/input.txt")
          input "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
          actual (get-marker input 14)
          ]
         (is (= actual expected))
         )
       )
  )
