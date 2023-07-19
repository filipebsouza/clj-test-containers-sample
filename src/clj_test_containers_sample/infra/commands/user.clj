(ns clj-test-containers-sample.infra.commands.user)

(defn insert-user
  "Insert users"
  [users]
  [:insert-into [:users]
   :values users])
