(ns mystore.controllers.product
	(:require [mystore.logic.product :as logic]
						[mystore.db.product :as db]))


(defn list-available-products []
	(let [products (db/findall)]
		(logic/remove-unavailable-products products)))