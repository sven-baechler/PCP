; Schreiben Sie die folgenden drei Ausdrücke als for-Comprehensions
; 1. Ausdruck
(mapcat (fn [x] (map #(+ x %) (range 3))) (range 3))

; als for-Comprehension
(for [x (range 3)
      y (range 3)]
  (+ x y))
; x = [0 1 2]
; y = [0 1 2]
; 2 verschachtelte for-Schleifen
; für jedes x wird jedes y dazu addiert

; 2. Ausdruck
(map #(list % %) (filter #(== (mod % 3) 0) (range 10)))

; als for-Comprehension
(for [x (range 10)
      :when (zero? (mod x 3))]
  (list x x))
; x = [0 1 2 3 4 5 6 7 8 9]
; wenn die Zahl durch 3 teilbar ist (modulo 3 = 0),
; Liste mit Zahl (dupliziert?) ausgeben

; 3. Ausdruck
(filter #(> (second %) (first %))
    (mapcat (fn [x] (map #(list x %) (range 4))) (range 4)))

; als for-Comprehension
(for [x (range 4)
      y (range 4)
      :when (> y x)]
  (list x y))
; x = [0 1 2 3]
; y = [0 1 2 3]
; wenn y > x,
; Liste mit Zahlen ausgeben
