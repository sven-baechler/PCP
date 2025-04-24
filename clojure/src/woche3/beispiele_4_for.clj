(ns woche3.beispiele-4-for)

; ------------------
; for-Comprehensions
; ------------------


(for [i [1 2 3 4]] (* 2 i))      ; (2 4 6 8)
(for [i (range 10)] (println i)) ; 0 1 2 3 4 5 6 7 8 9
(for [i (range 5)] (* i i))      ; (0 1 4 9 16)

(for [word ["cat" "dog" "mouse"]]
  (count word))                  ; (3 3 5)

(map (fn [i] (* 2 i)) [1 2 3 4])
(map println (range 10))
(map #(* % %) (range 5))
(map count ["cat" "dog" "mouse"])

(for [i [1 2] c [\a \b \c]]
  [i c])

(mapcat
 (fn [i]
   (map
    (fn [c] [i c])
    [\a \b \c]))
 [1 2])

(for [a (range 2)
      b (range 2)
      c (range 2)
      d (range 2)]
  (str a b c d))

; Alle Paare (i j)
(for [i [1 2 3]
      j [1 2 3]]
  [i j])

; Nur Paare, bei denen i < j
(for [i [1 2 3]
      j [1 2 3]
      :when (< i j)]
  [i j])

; Vorzeitiges Abbrechen
(for [i (range 1000)
      :while (< i 10)]
  i)