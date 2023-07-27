(ns clj-test-containers-sample.infra.commands.user)

(defn insert-user
  "Insert users"
  [id name]
  {:insert-into [:users]
   :columns [:id :name]
   :values [[id name]]})
