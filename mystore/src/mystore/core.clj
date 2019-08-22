(ns mystore.core
  (:gen-class)
  (:require [io.pedestal.http :as http]
  					[mystore.service :as service]))

(defn cria-servidor []
	(http/create-server
		{::http/routes	service/rotas
		::http/type :jetty
		::http/port 3000}))

(defn -main []
  (http/start (cria-servidor)))
