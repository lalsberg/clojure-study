(ns memory-game.controllers.room
  (:require [memory-game.datomic.dao :as dao]))

(defn generate-cards [card-size]
  (for [x (range 1 (inc card-size))]
    (str x)))

(defn create-room [player-name room-name card-size]
  (println (str player-name room-name card-size))
  (let [cards (generate-cards card-size)
        room-id (dao/insert-room room-name cards)
        player-id (dao/insert-player player-name room-id)]
    {:room-id room-id
     :player-id player-id}))

#_(defn join-room [room-id player-name]
  )