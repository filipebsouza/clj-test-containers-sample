(ns clj-test-containers-sample.infra.commands.user-test
  (:require [clojure.test :refer [is testing deftest]]
            [clj-test-containers-sample.infra.commands.user :as commands.user]))

(deftest insert-user-test
  (let [user-id 1
        user-name "John Doe"
        expected-command {:insert-into [:users]
                          :columns [:id :name]
                          :values [[user-id user-name]]}]

    (testing "Insert users"
      (is (= expected-command
             (commands.user/insert-user user-id user-name))))))
