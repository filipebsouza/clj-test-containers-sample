(ns clj-test-containers-sample.infra.db
  (:require [next.jdbc :as jdbc]
            [honey.sql :as sql]))

(defn get-datasource
  [host
   password
   port]
  (jdbc/get-datasource
   {:dbtype "postgresql"
    :dbname "postgres"
    :user "postgres"
    :password password
    :host host
    :port port}))

(defn get-connection
  [ds]
  (try
    (jdbc/get-connection ds)
    (catch Exception e
      (ex-data e))))

(defn execute!
  "Executes a command or query on the database"
  [conn cmd-or-query-map]
  (try
    (let [result (jdbc/execute! conn
                                (sql/format cmd-or-query-map))]
      (println "execute! result " result)
      result)
    (catch Exception e
      (ex-data e))))
