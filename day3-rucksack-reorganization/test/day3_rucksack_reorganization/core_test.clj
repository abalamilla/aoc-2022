(ns day3-rucksack-reorganization.core-test
  (:require [clojure.test :refer :all]
            [day3-rucksack-reorganization.core :refer :all]))

(deftest a-test
  (testing "Test item priorities"
    (let [
          actual 157
          input "vJrwpWtwJgWrhcsFMMfFFhFp\njqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\nPmmdzqPrVvPwwTWBwg\nwMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\nttgJtRGJQctTZtZT\nCrZsJsPPZsGzwwsLwLmpwMDw"
          expected (get-total-priorities input)
          ]
      (is (= expected actual))
      )
    )

  (testing "Test item priorities for grouped elf"
    (let [
          actual 70
          input "vJrwpWtwJgWrhcsFMMfFFhFp\njqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\nPmmdzqPrVvPwwTWBwg\nwMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\nttgJtRGJQctTZtZT\nCrZsJsPPZsGzwwsLwLmpwMDw"
          expected (get-total-priorities-v2 input)
          ]
      (is (= expected actual))
      )
    )
  )
