(ns hanoi.core
  (:gen-class))

(def field 
	[[{:text "  -  " :weight 1} {:text "  |  " :weight 0} {:text "  |  " :weight 0}]
	 [{:text " --- " :weight 2} {:text "  |  " :weight 0} {:text "  |  " :weight 0}]
	 [{:text "-----" :weight 3} {:text "  |  " :weight 0} {:text "  |  " :weight 0}]])

(def field-base-index
	(- (count field) 1))

(def get-top
	[column]
	
	)

(defn can-move
	[origin destiny]
	; (or 
	; 	(:weight ((field 2) destiny))
	; 	)
	)
(defn move
	[origin destiny]
	(if (can-move origin destiny)
		(println "can move")
		(println "cannot move"))
	)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println ((field 0) 0))
  (println (:text ((field 0) 0)))
  (println (:weight ((field 0) 1)))
  (println field-base-index)
  ; (println (move 1 2))
  )
