(ns integration.helpers.containers
  (:require [clj-test-containers.core :as tc]))

(def postgres-password "verysecret")

(defn create-postgres
  []
  (tc/create {:image-name    "postgres:12.1"
              :exposed-ports [5432]
              :env-vars      {"POSTGRES_PASSWORD" postgres-password}
              :wait-for {:strategy :healthy}}))

(defn start!
  [container]
  (tc/start! container))

(defn stop!
  [container]
  (tc/stop! container))
