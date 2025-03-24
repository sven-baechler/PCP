(ns SW05.u05-mitternachtsformel)

; Hilfsfunktion für die Berechnung der Diskriminante
(defn diskriminante [a b c] (- (* b b) (* 4 a c)))
(diskriminante 1 -3 2)
(diskriminante 1 -2 1)
(diskriminante 1 1 1)

; Aufgabe a) Schreiben Sie ein Prädikat, welches testet, ob eine Gleichung mit den Parametern a, b und c lösbar ist.
; Die Gleichung ist nicht lösbar, wenn die Diskriminante kleiner als 0 ist.
(defn loesbar? [a b c] (>= (diskriminante a b c) 0))
(loesbar? 1 -3 2)
(loesbar? 1 -2 1)
(loesbar? 1 1 1)

; Aufgabe b) Schreiben Sie eine Funktion, die die Koeffizienten a, b und c als Parameter entgegennimmt und die Anzahl der Lösungen zurückliefert (0, 1 oder 2).
(defn anzahl-loesungen [a b c]
(cond
  (< (diskriminante a b c) 0) 0 ; bei Diskriminante kleiner 0 gibt es keine Lösung
  (= (diskriminante a b c) 0) 1 ; bei Diskriminante gleich 0 gibt es genau 1 Lösung
  :else 2)) ; sonst gibt es immer 2 Lösungen
(anzahl-loesungen 1 -3 2)
(anzahl-loesungen 1 -2 1)
(anzahl-loesungen 1 1 1)

; Aufgabe c) Schreiben Sie eine Funktion, die einen Vektor ([…]) mit den Lösungen zurückliefert - dieser Vektor kann 0, 1 oder 2 Elemente haben.
(defn mitternachtsformel [a b c]
    (cond
      (< (diskriminante a b c) 0) [] ; keine Lösung, also leere Liste
      (= (diskriminante a b c) 0) [(/ (- b) (* 2 a))] ; Diskriminante gleich 0, also Liste mit einziger Lösung drin (-b über 2a)
      :else [(/ (+ (- b) (Math/sqrt (diskriminante a b c))) (* 2 a)) ; erste Lösung in der Liste (-b + Diskriminante über 2a)
             (/ (- (- b) (Math/sqrt (diskriminante a b c))) (* 2 a))])) ; zweite Lösung in der Liste (-b - Diskriminante über 2a)
(mitternachtsformel 1 -3 2)
(mitternachtsformel 1 -2 1)
(mitternachtsformel 1 1 1)
