(ns clojure-noob.core
  (:gen-class))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]

  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn do-do-alienize
	[part, numb]
	; ainda falta duplicar fazendo um set juntando com a parte right
	{:name (str (:name part) "-" numb)
	 :size (:size part)}
	)

(defn do-alienize
  [part, nums]

  (loop [remaining-nums nums
         final-alien-parts []]
         (if (empty? remaining-nums)
      		final-alien-parts
      		(let [[numb & rem-nums] remaining-nums]
      			(recur rem-nums 
      				(into final-alien-parts 
      					(set [(do-do-alienize part numb)])))))))

(defn alienize
  [part]
  (if (re-find #"^left-" (:name part)) 
  	(do-alienize part (list 1 2 3 4 5))
  	(set [part])))

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (alienize part)))))))
; >>>>> OLD
; (defn symmetrize-body-parts
;   "Expects a seq of maps that have a :name and :size"
;   [asym-body-parts]
;   (loop [remaining-asym-parts asym-body-parts
;          final-body-parts []]
;     (if (empty? remaining-asym-parts)
;       final-body-parts
;       (let [[part & remaining] remaining-asym-parts]
;         (recur remaining
;                (into final-body-parts
;                      (set [part (matching-part part)])))))))
;                      ; (set [part])))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (symmetrize-body-parts asym-hobbit-body-parts))
  ; (println (do-alienize (first asym-hobbit-body-parts) (list 1 2 3 4 5)))
)
