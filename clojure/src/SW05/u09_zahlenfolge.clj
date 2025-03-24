(ns SW05.u09-zahlenfolge)

(defn print-number [n]
  (when (> n 1) ; condition
    (println n) ; Zahl ausgeben
    (if (zero? (rem n 2)) ; Prüfen ob die Zahl gerade ist (modulo)
      (print-number (/ n 2)) ; wenn gerade, mit n/2 aufrufen
      (print-number (+ (* 3 n) 1))))) ; wenn ungerade, mit 3n + 1aufrufen

(print-number 3)
(print-number 7)
