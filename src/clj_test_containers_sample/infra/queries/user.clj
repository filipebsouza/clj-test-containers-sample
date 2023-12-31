(ns clj-test-containers-sample.infra.queries.user)

(defn find-by-id
  "Find a user by id"
  [id]
  {:select [:id :name]
   :from [:users]
   :where [:= :id id]})
