(ns mystore.controllers.product
	(:require [mystore.logic.product :as logic]
						[mystore.db.product :as db]))

(defn list-abailable-products []
	(let [products (db/find)]
		(logic/remove-unavailable-products products)))