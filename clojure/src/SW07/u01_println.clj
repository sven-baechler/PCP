(defn sum-numbers [nums]
  (map println nums) ; println wird nie ausgeführt
  (reduce + nums))

(sum-numbers (range 10))

; Warum wird println nie ausgeführt?
; Weil map in Clojure lazy ist (wird erst ausgewertet, wenn es gebraucht wird).
; Ergebnis von map wird nie gebraucht, also wird println auch nie ausgewertet.

; Finden Sie zwei Möglichkeiten in Clojure, wie Sie die Ausgabe der Zahlen mit println dennoch erreichen können.
; 1. Lösung: mit doall
(defn sum-numbers-new1 [nums]
  (doall (map println nums)) ; doall erzwingt die vollständige Auswertung einer lazy sequence
  (reduce + nums))

(sum-numbers-new1 (range 10))

; 2. Lösung: mit run!
(defn sum-numbers-new2 [nums]
  (run! println nums) ; run! führt eine Funktion auf jedes Vektor-Element mit Side Effects aus (nicht lazy)
  (reduce + nums))

(sum-numbers-new2 (range 10))
