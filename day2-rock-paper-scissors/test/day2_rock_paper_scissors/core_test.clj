(ns day2-rock-paper-scissors.core-test
  (:require [clojure.test :refer :all]
            [day2-rock-paper-scissors.core :refer :all]))

(deftest a-test
  (testing "Follow as is"
    (let [
          actual 15
          input "A Y\nB X\nC Z"
          expected (play input)
          ]
      (is (= expected actual))
      )
    )

  (testing "Choose appropriate..."
    (let [
          actual 12
          input "A Y\nB X\nC Z"
          expected (play-v2 input)
          ]
      (is (= expected actual))
      )
    )
  )
