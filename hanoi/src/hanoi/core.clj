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
	 [{:text "-----" :weight 3} empty-cell empty-cell]])

(def field-base-index (- (count field) 1))

(defn print-field
	[field]
	(let [s (clojure.string/join "\n" (map clojure.string/join field))] (println s)))

; (defn print-field
; 	[]
; 	(for [line (range 0 (+ field-base-index 1))]
; 		; (print {:text ((field line) 0)})
; 		; (print {:text ((field line) 1)})
; 		; (println {:text ((field line) 2)})
; 		))

(defn get-column
	[field column]
	(for [line (range 0 (+ field-base-index 1))]
		((field line) column)))

(defn get-top
	[field column]
	(first
			(filter has-piece (get-column field column))))

(defn get-destiny-line
	[field column]
	(first
		(for [line (range field-base-index -1 -1)
			:when (is-empty ((field line) column))]
			line)))

(defn get-origin-line
	[field column]
	(first
		(for [line (range 0 (+ field-base-index 1))
			:when (has-piece ((field line) column))]
			line)))

(defn can-move
	[field origin destiny]
	(and
		(not-nil? (get-top field origin))
		(or
			(nil? (get-top field destiny))
			(< (:weight (get-top field origin)) (:weight (get-top field destiny))))))

(defn update-destiny
	[field origin destiny]
	(assoc field (get-destiny-line field destiny) 
		(assoc (field (get-destiny-line field destiny)) destiny (get-top field origin))))

(defn update-origin
	[field origin]
	(assoc field (get-origin-line field origin) 
		(assoc (field (get-origin-line field origin)) origin empty-cell)))

(defn update-destiny-and-origin
	[field origin destiny]
	(update-origin
		(update-destiny field origin destiny) origin))

(defn move
	[field origin destiny]
	(if (can-move field origin destiny)
		(update-destiny-and-origin field origin destiny)
		(println "cannot move")))

(defn ask
	[field moves]
	(println "Origem: ")
	(def origin (- (Integer. (read-line)) 1))
	(println "Destino: ")
	(def destiny (- (Integer. (read-line)) 1))

	(let [field (move field origin destiny)]
		(print-field field)
		(println (str "Movimentos: " moves))
		(ask field (inc moves)))
)

(defn -main
	"I don't do a whole lot ... yet."
	[& args]

	(print-field field)
	(ask field 0)  
)
