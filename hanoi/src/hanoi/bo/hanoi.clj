(ns hanoi.bo.hanoi)

(def empty-cell {:text "      |      " :weight 0})

(def not-nil? (complement nil?))

(defn has-piece
	[cell]
	(> (:weight cell) 0))

(def is-empty (complement has-piece))

(defn get-cell 
	[field line column] 
	((field line) column))

(def get-cell)
                                                         
(def field 
	[[{:text "      -      " :weight 1} empty-cell empty-cell]
	 [{:text "     ---     " :weight 2} empty-cell empty-cell]
	 [{:text "    -----    " :weight 3} empty-cell empty-cell]
	 [{:text "   -------   " :weight 4} empty-cell empty-cell]
	 [{:text "  ---------  " :weight 5} empty-cell empty-cell]
	 [{:text " ----------- " :weight 6} empty-cell empty-cell]
	 [{:text "-------------" :weight 7} empty-cell empty-cell]])

(def field-base-index (- (count field) 1))

(defn get-text [cell] (:text cell))

(defn format-line [line] (map get-text line))

(defn get-column
	[field column]
	(for [line (range 0 (+ field-base-index 1))]
		(get-cell field line column)))

(defn get-top
	[field column]
	(first
			(filter has-piece (get-column field column))))

(defn get-destiny-line
	[field column]
	(first
		(for [line (range field-base-index -1 -1)
			:when (is-empty (get-cell field line column))]
			line)))

(defn get-origin-line
	[field column]
	(first
		(for [line (range 0 (+ field-base-index 1))
			:when (has-piece (get-cell field line column))]
			line)))

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
		(update-destiny-and-origin field origin destiny))

(defn has-won 
	[field]
	(or
		(empty? (filter is-empty (get-column field 1)))
		(empty? (filter is-empty (get-column field 2)))))

(defn can-move
	[field origin destiny]
	(and
		(not= origin destiny)
		(some #(= origin %) '(0 1 2))
		(some #(= destiny %) '(0 1 2))
		(not-nil? (get-top field origin))
		(or
			(nil? (get-top field destiny))
			(< (:weight (get-top field origin)) (:weight (get-top field destiny))))))