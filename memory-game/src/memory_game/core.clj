(ns memory-game.core
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [memory-game.service :as service]))

(defn create-server []
  (http/create-server
   {::http/routes service/routes
    ::http/type   :jetty
    ::http/port   3000}))

(defn -main []
  (http/start (create-server)))