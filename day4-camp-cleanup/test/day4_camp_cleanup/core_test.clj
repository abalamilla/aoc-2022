(ns day4-camp-cleanup.core-test
  (:require [clojure.test :refer :all]
            [day4-camp-cleanup.core :refer :all]))

(deftest a-test
  (testing "Overlaps"
    (let [
          expected 2
          input "2-4,6-8\n2-3,4-5\n5-7,7-9\n2-8,3-7\n6-6,4-6\n2-6,4-8"
          actual (count-overlaps input)]
      (is (= actual expected))
      )
    )

  (testing "Intersections"
    (let [
          expected 4
          input "2-4,6-8\n2-3,4-5\n5-7,7-9\n2-8,3-7\n6-6,4-6\n2-6,4-8"
          actual (count-intersections input)]
      (is (= actual expected))
      )
    )
  )
