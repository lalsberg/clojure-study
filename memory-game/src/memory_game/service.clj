(ns memory-game.service
  (:gen-class)
  (:require [io.pedestal.http.route.definition.table :as table]
            [io.pedestal.http.body-params :as body-params]
            [memory-game.controllers.room :as room-controller]
            [memory-game.datomic.dao :as dao]
            [clojure.data.json :as json]))

(defn create-room [request]
  (let [json-request (:json-params request)
        player-name (:player-name json-request)
        room-name (:name (:room json-request))
        card-size (:card-size (:room json-request))]
    {:status 200
     :body (json/write-str
            (room-controller/create-room player-name room-name card-size))
     :headers {"Content-Type" "application/json"}}))

(defn list-rooms [request]
  {:status 200
   :body (dao/list-rooms)
   :headers {"Content-Type" "application/json"}})

(def routes
  (table/table-routes
   [["/list-rooms" :get list-rooms :route-name :list-rooms]
    ["/create-room" :post [(body-params/body-params) create-room] :route-name :create-room]]))