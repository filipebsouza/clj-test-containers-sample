(ns integration.insert-user-test
  (:require [clj-test-containers-sample.infra.commands.user :as commands.user]
            [clj-test-containers-sample.infra.db :as db]
            [clj-test-containers-sample.infra.definitions.users :as definitions.users]
            [clojure.test :refer [deftest is testing use-fixtures]]
            [integration.helpers.containers :as containers]))

(def data-source (atom nil))

(use-fixtures :once
  (fn [f]
    (let [postgres (-> (containers/create-postgres)
                       (containers/start!))]
      (reset! data-source (db/get-datasource
                           (:host postgres)
                           containers/postgres-password
                           (get (:mapped-ports postgres) containers/postgres-port)))
      (with-open [conn (db/get-connection @data-source)]
        (db/execute! conn definitions.users/create-users))
      (f)
      (containers/stop! postgres))))

(deftest insert-user-test
  (with-open [conn (db/get-connection @data-source)]
    (testing "insert users"
      (is (= {:id 1 :name "John Doe"}
             (db/execute! conn (commands.user/insert-user [{:id 1 :name "John Doe"}])))))))
