(ns SW05.u08-rekursion)

; Aufgabe a) Schreiben Sie eine rekursive Funktion welche die ganzen Zahlen von a bis b aufsummiert (b inklusive)
; Verwenden Sie ausschliesslich einfache Funktionen wie inc, dec, + und rekursive Aufrufe.
(defn sum-range [a b]
  (if (> a b) 0 ; wenn a grösser als b ist, ist die Antwort 0
      (+ a (sum-range (inc a) b)))) ; a wird mit dem Ergebnis des rekursiven Aufrufs (mit a++) aufsummiert. b bleibt gleich. 

(sum-range 1 5) ; ergibt 15
(sum-range 3 9) ; ergibt 42

; Aufgabe b) Erweitern Sie die Funktion, so dass die Quadratzahlen von a bis b aufsummiert werden.
(defn sum-squares [a b]
  (if (> a b) 0
      (+ (* a a) (sum-squares (inc a) b)))) ; einziger Unterschied zu sum-range: wir addieren das Quadrierte a (* a a) zum rekursiven Aufruf hinzu.

(sum-squares 1 4) ; 1^2 + 2^2 + 3^2 + 4^2 = 30
