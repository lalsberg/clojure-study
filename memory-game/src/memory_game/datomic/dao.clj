(ns memory-game.datomic.dao
  (:require [datomic.client.api :as d]))

(def cfg {:server-type :peer-server
          :access-key "myaccesskey"
          :secret "mysecret"
          :endpoint "localhost:8998"
          :validate-hostnames false})

(def client (d/client cfg))

(def conn (d/connect client {:db-name "hello"}))

(defn new-db [] (d/db conn))

(def all-rooms-query '[:find ?e ?title ?open
                       :where [?e :room/title ?title]
                       [?e :room/open ?open]])
(defn list-rooms []
  (d/q all-rooms-query (new-db)))

(defn room [room-name cards]
  {
   :room/title room-name
   :room/cards (clojure.string/join "," cards)
   :room/open true
   })

(defn get-id [insert-result]
  (:e (second (:tx-data insert-result))))

(defn insert-room [room-name cards]
  (get-id
   (d/transact conn {:tx-data [(room room-name cards)]})))

(defn player [player-name room-id]
  {:player/name player-name
   :player/room room-id})

(defn insert-player [player-name room-id]
  (get-id
   (d/transact conn {:tx-data [(player player-name room-id)]})))
