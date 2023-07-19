(ns clj-test-containers-sample.infra.commands.user-test
  (:require [clojure.test :refer [is testing deftest]]
            [clj-test-containers-sample.infra.commands.user :as commands.user]))

(deftest insert-user-test
  (let [user-to-insert {:id 1 :name "John Doe"}
        expected-command [:insert-into [:users]
                          :values [user-to-insert]]]

    (testing "Insert users"
      (is (= (commands.user/insert-user [user-to-insert])
             expected-command)))))
