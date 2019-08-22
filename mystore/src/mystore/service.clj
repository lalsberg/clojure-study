(ns mystore.service
	(:gen-class)
	(:require [io.pedestal.http.route.definition.table :as table]
						[mystore.controllers.product :as controller]))

(defn list-available-products [request]
	{:status 200
	 :body (controller/list-available-products)
	 :headers {"Content-Type" "application/json"}})

(def rotas 
	(table/table-routes 
		[["available-products" :get list-available-products :route-name :list-available-products]]))