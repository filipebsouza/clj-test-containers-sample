(ns clj-test-containers-sample.infra.db
  (:require [next.jdbc :as jdbc]
            [honey.sql :as sql]))

(defn create-integration-test-cfg
  [host
   password]
  {:dbtype "postgresql"
   :dbname "postgres"
   :host host
   :user "postgres"
   :password password
   :port 5432})

(defn execute!
  "Executes a command or query on the database"
  [db-cfg cmd-or-query-map]
  (jdbc/execute! (jdbc/get-datasource db-cfg)
                 (sql/format cmd-or-query-map)))

(comment

  (sql/format {:create-table :users
               :with-columns
               [[:id :int [:not nil]]
                [:name [:varchar 32] [:not nil]]]}))
