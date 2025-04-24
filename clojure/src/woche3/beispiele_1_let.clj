(ns woche3.beispiele-1-let)

; -------------
; let-Bindungen
; -------------

(let [a 1 b 2]
  (+ a b)) ; liefert 3

(defn quader-oberfläche [länge breite höhe]
  (let [lw (* länge breite)
        lh (* länge höhe)
        wh (* breite höhe)]
    (* 2 (+ lw lh wh))))

(quader-oberfläche 2 3 4) ; liefert 52

(defn solve-quadratic [a b c]
  (let [discriminant (- (* b b) (* 4 a c))
        denominator (* 2 a)]
    (cond
      (< discriminant 0) nil
      (= discriminant 0) (/ (- b) denominator)
      :else
      (let [sqrt-discriminant (Math/sqrt discriminant)]
        [(/ (+ (- b) sqrt-discriminant) denominator)
         (/ (- (- b) sqrt-discriminant) denominator)]))))

(solve-quadratic 1 -3 2) ; liefert [2.0 1.0]

(defn mittelwert [coll]
  (let [summe (reduce + coll)
        anzahl (count coll)]
    (/ summe anzahl)))

(defn varianz [coll]
  (let [quadrat (fn [x] (* x x))
        mittel (mittelwert coll)
        abweichungen (map #(- % mittel) coll)
        quadratische-abweichungen (map quadrat abweichungen)]
    (mittelwert quadratische-abweichungen)))

(varianz [1 2 3 4 5]) ; liefert 2.0

(let [a 1 b 5]
  (let [a 2 b 6]
    (let [a 3]
      (+ a b))))

(let [a 1 b 2 c 3]
  (let [b c]
    (let [a b]
      (let [c a]
        [a b c]))))


; loop führt lokale Definitionen ein
(loop [n 0]
  (when (< n 10)
    (println n)
    (recur (inc n))))

; fn führt lokale Definitionen ein
((fn [a b] (+ a b)) 1 2)

; for kann auch lokale Definitionen einführen (etwas später)
(for [i (range 10)] (* i i))


(def li [1 2 3 4])

(let [[head & tail] li] [head tail])

(let [[a b c] li] [a b c])

(let [[a b & c] li] [a b c])

(let [[[hd1 & tl1] [hd2 & tl2]] [[1 2 3] [4 5 6]]]
  (println hd1 tl1 hd2 tl2))

(def point {:x 1 :y 2})
(let [{x :x y :y} point] [x y])

