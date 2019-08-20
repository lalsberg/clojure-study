(ns hanoi.core
  (:gen-class))

(require 'hanoi.bo.hanoi)
(refer 'hanoi.bo.hanoi)

(defn clear-screen [] (print (str (char 27) "[2J")))

(defn print-field
	[field]
		(println 
			"  _   _                   _   _____                      \n"
			"| | | |                 (_) |_   _|                     \n"
			"| |_| | __ _ _ __   ___  _    | | _____      _____ _ __ \n"
			"|  _  |/ _` | '_ \\ / _ \\| |   | |/ _ \\ \\ /\\ / / _ \\ '__|\n"
			"| | | | (_| | | | | (_) | |   | | (_) \\ V  V /  __/ |   \n"
			"\\_| |_/\\__,_|_| |_|\\___/|_|   \\_/\\___/ \\_/\\_/ \\___|_|   \n"
			"                                                        \n"
			"                                                        \n")
		(dorun (map print (format-line (field 0))))
		(println)
		(dorun (map print (format-line (field 1))))
		(println)
		(dorun (map print (format-line (field 2))))
		(println)
		(dorun (map print (format-line (field 3))))
		(println)
		(dorun (map print (format-line (field 4))))
		(println)
		(dorun (map print (format-line (field 5))))
		(println)
		(dorun (map print (format-line (field 6))))
		(println))

(defn ask
	([field] (ask field 0))
	([field moves]
	(clear-screen)
	(print-field field)	
	(if (has-won field) 
		(println (str "Parabens!! Movimentos:" moves))
		(do 
			(println "Origem (1,2,3): ")
			(def origin (- (Integer. (read-line)) 1))
			(clear-screen)
			(print-field field)
			(println "Destino (1,2,3): ")
			(def destiny (- (Integer. (read-line)) 1))

			(if (can-move field origin destiny)
				(ask (move field origin destiny) (inc moves))
				(ask field moves))))))

(defn -main
	"I don't do a whole lot ... yet."
	[& args]
	(def great-books ["East of Eden" "The Glass Bead Game"])
	(println (ns-interns *ns*))
	; (println (ns-map *ns*))
	(print-field field)
	(ask field)
)
