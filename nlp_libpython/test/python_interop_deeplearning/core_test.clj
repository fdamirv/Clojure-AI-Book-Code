(ns nlp-libpython-spacy.core-test
  (:require [clojure.test :as test]
            [nlp-libpython-spacy.core :as sp]))

(def test-text "John Smith worked for IBM in Mexico last year and earned $1 million in salary and bonuses.")

(test/deftest a-test
  (test/testing "FIXME, I fail."
    (test/is (= 033 (count (sp/text->tokens test-text))))))
