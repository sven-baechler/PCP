(ns SW05.u04-bedingungsklauseln)

; Aufgabe a)
(def temperatur 40)
(cond
  (> temperatur 35) "heiss"
  (> temperatur 25) "warm"
  (> temperatur 15) "mittel"
  :else "kalt")
; Die Bedingungsklauseln dürfen nicht vertauscht werden, da sonst z.B. bei 40 mittel ausgegeben wird.


; Aufgabe b)
(def zahl 12)
(cond
  (zero? (rem zahl 2)) "durch 2 teilbar"
  (zero? (rem zahl 3)) "durch 3 teilbar"
  :else "weder durch 2 noch durch 3 teilbar")
; Die Funktionsweise des Programms wird verändert wenn die Bedingungsklauseln vertauscht werden.
; Es wird "durch 2 teilbar" anstatt "durch 3 teilbar" ausgegeben.

; Aufgabe c)
  (if (> temperatur 35)
    "heiss"
    (if (> temperatur 25)
      "warm"
      (if (> temperatur 15)
        "mittel"
        "kalt")))

; Aufgabe d)
; case macht einen Test auf Wertgleichheit. Die Temperaturen aber auch abweichen vom zu vergleichenden Wert --> also ungeeignet.