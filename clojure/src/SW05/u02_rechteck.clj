(ns SW05.u02-rechteck)

; Aufgabe 2a)
; Länge und Breite definieren
(def laenge 5)
(def breite 6)

; Fläche berechnen
(defn flaeche [a b] (* a b))
(flaeche laenge breite)

; Umfang berechnen
(defn umfang [a b] (* 2 (+ a b)))
(umfang laenge breite)

; Diagonale berechnen
(defn diagonale [a b] (Math/sqrt (+ (* a a) (* b b))))
(diagonale laenge breite)


; Aufgabe 2b)
(def hoehe 10)

; Volumen berechnen
(defn volumen [a b c] (* a b c))
(volumen laenge breite hoehe)

; Oberfläche berechnen
(defn oberflaeche [a b c] (* 2 (+ (* a b) (* a c) (* b c))))
(oberflaeche laenge breite hoehe)


; Aufgabe 2c)
; schon gemacht
