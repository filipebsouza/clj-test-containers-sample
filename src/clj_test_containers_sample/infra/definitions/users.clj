(ns clj-test-containers-sample.infra.definitions.users)

(def create-users
  {:create-table :users
   :with-columns
   [[:id :int [:not nil]]
    [:name [:varchar 32] [:not nil]]]})
