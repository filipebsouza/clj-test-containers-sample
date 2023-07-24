(ns integration.helpers.containers
  (:require [clj-test-containers.core :as tc]))

(def postgres-password "verysecret")
(def postgres-port 5432)

(defn create-postgres
  []
  (tc/create {:image-name    "postgres:12.1"
              :exposed-ports [postgres-port]
              :env-vars      {"POSTGRES_PASSWORD" postgres-password}}))

(defn start!
  [container-config]
  (let [container (tc/start! container-config)]
    (Thread/sleep 3000)
    container))

(defn stop!
  [container]
  (tc/stop! container))
