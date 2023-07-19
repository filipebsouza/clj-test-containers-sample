(ns integration.insert-user-test
  (:require [clj-test-containers-sample.infra.commands.user :as commands.user]
            [clj-test-containers-sample.infra.db :as db]
            [clj-test-containers-sample.infra.definitions.users :as definitions.users]
            [clojure.test :refer [deftest is testing]]
            [integration.helpers.containers :as containers]))

(defn- tear-down!
  [container]
  (containers/stop! container))

(deftest insert-user-test
  (let [postgres (containers/create-postgres)
        _ (containers/start! postgres)
        db-cfg (db/create-integration-test-cfg (:host postgres)
                                               containers/postgres-password)
        _ (println db-cfg)
        _ (db/execute! db-cfg definitions.users/create-users)
        user-to-insert {:id 1 :name "John Doe"}
        command (commands.user/insert-user [user-to-insert])]

    (testing "insert users"
      (is (= 1
             (db/execute! db-cfg command))))

    (tear-down! postgres)))
