(ns mystore.db.product
	(:require [org.clojure/java.jdbc :as jdbc]))

(def connection-data {
	:dbtype "mysql"
	:dbname "mystore"
	:user "root"
	:password ""
	:host "localhost"
	:port "3306"
})

(jdbc/query connection-data ["select * from product"])