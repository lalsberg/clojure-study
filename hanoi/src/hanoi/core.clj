(ns hanoi.core
  (:gen-class))

(def empty-cell {:text "  |  " :weight 0})

(def field 
	[[{:text "  -  " :weight 1} empty-cell empty-cell]
	 [{:text " --- " :weight 2} empty-cell empty-cell]
	 [{:text "-----" :weight 3} empty-cell empty-cell]])

(def field-base-index
	(- (count field) 1))

(defn get-column
	[column]
	(for [line (range 0 (+ field-base-index 1))]
		((field line) column))
	)

(defn has-piece
	[cell]
	(> (:weight cell) 0))

(defn get-top
	[column]

	(first
		(into []
			(filter has-piece (get-column column)))))

(defn can-move
	[origin destiny]
	(or 
		(:weight ((field 2) destiny))
		)
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
  (println (get-column 1))
  (println (get-top 0))
  ; (println (move 1 2))
  )
