(ns SW05.u07-umrechner)

; Aufgabe a) Schreiben Sie eine Funktion, die CHF-Betrag in Euro umrechnet.
(defn chf-in-euro [chf] (* chf 1.05))
(chf-in-euro 10)

; Aufgabe b) Schreiben Sie eine Funktion, die einen Celsius-Wert in Fahrenheit umrechnet.
(defn celsius-in-farenheit [celsius] (+ 32 (* celsius 1.8)))
(celsius-in-farenheit 30)

; Aufgabe c)
; Die ersten beiden Parameter sind hier zwei Referenzwerte auf der Celsius-Skala, und
; danach folgen die beiden Werte in Fahrenheit, die diesen Celsius-Temperaturen
; entsprechen. Der letzte Wert ist der Wert, der von der ersten in die zweite Skala
; umgerechnet werden soll.
(defn scale [c1 c2 f1 f2 x]
  (+ f1 (* (/ (- f2 f1) (- c2 c1)) (- x c1))))
; Vom Punkt 0 / 100 und 100 / 212 eine Gerade ziehen, dann kann jeder Punkt auf der Geraden ermittelt werden.

(scale 0.0 100.0 32.0 212.0 30.0)

; Wie könnte man damit auch Währungen umrechnen?
(scale 1 100 1.05 105 10)
