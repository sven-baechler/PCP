; Erzeugen Sie die folgenden Zahlenfolgen mit loop … recur:

; 1 2 4 8 16 32 64 128 256
(loop [n 1 result []]
  (if (> n 256)
    result
    (recur (* 2 n) (conj result n))))

; 1 2 4 7 11 16 22 29 37 48 58
(loop [n 1 delta 1 result []]
  (if (> n 58)
    result
    (recur (+ n delta) (inc delta) (conj result n))))

; 1 3 2 4 3 5 4 6 5 7 6 8 7 9
(loop [a 1 b 3 result []]
  (if (> b 9)
    result
    (recur (inc a) (inc b) (conj result a b))))
