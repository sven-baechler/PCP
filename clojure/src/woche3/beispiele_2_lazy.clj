(ns woche3.beispiele-2-lazy)

; --------------
; Lazy Sequences
; --------------

(defn square [x] (* x x))

(take 10
      (map square
           (range 10000000000000000000000000000)))

(defn sum-numbers [nums]
  (map println nums) ; println wird nie ausgeführt
  (reduce + nums))
(sum-numbers (range 10))

(repeat "Mayday") ; "Mayday", "Mayday", "Mayday", ...

(iterate inc 1) ; 1, 2, 3, ...

(iterate (partial * 2) 1) ; 1, 2, 4, 8, 16, ...

(map square
     (iterate inc 1)) ; 1, 4, 9, 16, 25, ...

(interleave
 (iterate inc 1)
 (repeat "Hello"))

(range)
