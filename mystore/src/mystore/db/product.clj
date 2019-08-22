(ns mystore.db.product
	(:require [clojure.java.jdbc :as jdbc]))

(def connection-data {
	:dbtype "mysql"
	:dbname "mystore"
	:user "root"
	:password "root"
	:host "localhost"
	:port 3306})

(defn findall []
	(jdbc/query connection-data ["select * from product"]))