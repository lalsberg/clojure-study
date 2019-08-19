(ns hanoi.core
  (:gen-class))

(def empty-cell {:text "  |  " :weight 0})

(def not-nil? (complement nil?))

(defn has-piece
	[cell]
	(> (:weight cell) 0))

(def is-empty (complement has-piece))

(def field 
	[[{:text "  -  " :weight 1} empty-cell empty-cell]
	 [{:text " --- " :weight 2} empty-cell empty-cell]
	 [{:text "-----" :weight 3} {:text "-----" :weight 3} empty-cell]])

(def field-base-index (- (count field) 1))

(defn print-field
	[]
	(map #(print %)
	(for [line (range 0 (+ field-base-index 1))]
		)))

; (defn print-field
; 	[]
; 	(for [line (range 0 (+ field-base-index 1))]
; 		; (print {:text ((field line) 0)})
; 		; (print {:text ((field line) 1)})
; 		; (println {:text ((field line) 2)})
; 		))

(defn get-column
	[column]
	(for [line (range 0 (+ field-base-index 1))]
		((field line) column)))

(defn get-top
	[column]
	(first
			(filter has-piece (get-column column))))

(defn get-destiny-line
	[column]
	(first
		(for [line (range field-base-index -1 -1)
			:when (is-empty ((field line) column))]
			line)))

(defn can-move
	[origin destiny]
	(and
		(not-nil? (get-top origin))
		(or
			(nil? (get-top destiny))
			(< (:weight (get-top origin)) (:weight (get-top destiny))))))

(defn teste
	[origin destiny]
	(assoc field (get-destiny-line destiny) 
		(assoc (field (get-destiny-line destiny)) destiny (get-top origin)))
)

(defn teste2
	[destiny]
	(field (get-destiny-line destiny))
)

(defn move
	[origin destiny]
	(if (can-move origin destiny) 
			; (assoc-in (field (get-destiny-line destiny)) destiny (get-top origin)))

		(println "cannot move")
	))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println ((field 0) 0))
  (println (:text ((field 0) 0)))
  (println (:weight ((field 0) 1)))
  (println (get-column 1))
  (println (get-top 0))
  (println (can-move 0 1))
  (println (can-move 1 2))
  (println (get-column 0))
  (println (get-destiny-line 1))
  (println (teste2 1))
  (println (teste 0 1))
  (print-field)
  ; (println (move 1 2))
  )
