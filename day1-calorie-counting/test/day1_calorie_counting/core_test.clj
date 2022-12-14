(ns day1-calorie-counting.core-test
  (:require [clojure.test :refer :all]
            [day1-calorie-counting.core :refer :all]))

(deftest a-test
  (testing "Day 1 - part 1"
    (let [
          input "1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000"
          expected 24000
          actual (max-calories input)]
      (is (= actual expected))
      )
    )

  (testing "Day 1 - part 2"
    (let [
          input "1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000"
          expected 45000
          actual (top-three input)]
      (is (= actual expected))
      )
    )
  )
