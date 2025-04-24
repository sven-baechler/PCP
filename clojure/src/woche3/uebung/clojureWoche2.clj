(ns woche3.uebung.clojureWoche2)

; rechnet eine einzelne Operation aus
(defn run-command [stack command] ; stack = Liste von Zahlen, command = "LOAD 5" o.ä.
  (let [parts (.split command " ") ; Operation und Zahl aufsplitten und in der Liste parts speichern
        op (first parts)] ; op mit dem ersten Element der parts Liste initialisieren (Operator)
    (cond
      (= op "LOAD") (cons (Integer/parseInt (second parts)) stack) ; zweites Element von parts (Zahl) als Integer parsen und auf den Stack legen
      (= op "ADD")  (cons (+ (first stack) (second stack)) (drop 2 stack)) ; 1. und 2. Element des Stacks addieren und löschen, Summe auf den Stack legen
      (= op "MUL")  (cons (* (first stack) (second stack)) (drop 2 stack)) ; 1. und 2. Element des Stacks multiplizieren und löschen, Produkt auf den Stack legen
      (= op "SUB")  (cons (- (second stack) (first stack)) (drop 2 stack)) ; 2. Element des Stacks vom 1. Element subtrahieren und beide löschen, Differenz auf den Stack legen
      (= op "DIV")  (cons (quot (second stack) (first stack)) (drop 2 stack))))) ; 2. Element des Stacks durch 1. Element dividieren und beide löschen, Ergebnis auf den Stack legen

; rechnet ein ganzes Programm mit mehreren Operationen aus
(defn run-program [program]
  (first (reduce run-command '() program))) ; führt run-command aus (mit leerem Stack) und gibt den obersten Wert des Stack zurück (mit first)

(run-command [] "LOAD 1") ; liefert (1)
(run-command [1 2] "LOAD 3") ; liefert (3 1 2)
(run-command [1 2 9 9 9] "ADD") ; liefert (3 9 9 9)
(run-command [2 3 9 9 9] "MUL") ; liefert (6 9 9 9)


(run-program ["LOAD 2" "LOAD 3" "ADD" "LOAD 4" "MUL"])

(run-command [4 7 9 9 9] "SUB")
