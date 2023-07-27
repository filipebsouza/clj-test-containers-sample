(ns benchmark.postgres-container-bench
  (:require [criterium.core :as criterium]
            [clj-test-containers.core :as tc]))

(defn create-and-start-container []
  (criterium/with-progress-reporting
    (criterium/quick-bench
     (let [container (-> (tc/create {:image-name    "postgres:12.1"
                                     :exposed-ports [5432]
                                     :env-vars      {"POSTGRES_PASSWORD" "verysecret"}})
                         (tc/start!))]
       (Thread/sleep 3000)
       (tc/stop! container))
     :verbose)))

(comment
  (create-and-start-container)

  (criterium/with-progress-reporting (criterium/bench (Thread/sleep 3000) :verbose)))
