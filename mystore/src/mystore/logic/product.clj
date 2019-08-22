(ns mystore.logic.product)

(defn available-product? 
	[product]
	(not (= (get product :quantity) 0)))

(defn remove-unavailable-products
	[products]
	(filter available-product? products))