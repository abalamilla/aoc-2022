(ns day5-supply-stacks.core-test
  (:require [clojure.test :refer :all]
            [day5-supply-stacks.core :refer :all]))

(deftest a-test
  (testing "Top stacks"
    (let [
          expected "CMZ"
          input "    [D]    \n[N] [C]    \n[Z] [M] [P]\n 1   2   3 \n\nmove 1 from 2 to 1\nmove 3 from 1 to 3\nmove 2 from 2 to 1\nmove 1 from 1 to 2"
          actual (get-top-stacks input move)
          ]
      (is (= actual expected))
      )
    )

  (testing "Top stacks V2"
    (let [
          expected "MCD"
          input "    [D]    \n[N] [C]    \n[Z] [M] [P]\n 1   2   3 \n\nmove 1 from 2 to 1\nmove 3 from 1 to 3\nmove 2 from 2 to 1\nmove 1 from 1 to 2"
          actual (get-top-stacks input move-v2)
          ]
      (is (= actual expected))
      )
    )
  )
