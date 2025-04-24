(ns woche3.uebung.aufgabe4
  (:require [woche3.uebung.parser :refer [parse]])
  (:require [woche3.uebung.clojureWoche2 :refer [run-program]])
  (:import [woche3.uebung.parser Op Val]))

; Beispiel: Baum für (2+3) * 4
(def expr1 (Op. "*" (Op. "+" (Val. 2) (Val. 3)) (Val. 4)))

; Beispiel: Baum für (2+3) * 4, aber mit parse
(def expr2 (parse "(2+3) * 4"))

(= expr1 expr2) ; gibt true, weil die Bäume identisch sind

; Aufgabe b)
; Erzeugen Sie den Baum für Ausdruck 2 * (4-2) + (6/3) von Hand, indem Sie wie im Beispiel Opund Val-Knoten erzeugen.
(def expr3 (Op. "+"
                (Op. "*"
                     (Val. 2)
                     (Op. "-" (Val. 4) (Val. 2)))
                (Op. "/" (Val. 6) (Val. 3))))

; Aufgabe c1)
; Nutzen Sie die parse-Funktion, um den Baum aus a.) zu erzeugen
(def expr4 (parse "2 * (4-2) + (6/3)"))

; Aufgabe c2)
; Vergleichen Sie Ihren Baum mit dem Baum von der parse-Funktion (die Bäume können mit = verglichen werden und sollten identisch sein).
(= expr3 expr4)

; ------------------------------------------------
; TODO - Implementieren Sie die Funktion eval-expr
; 
; Tipp: Im Input wurde gezeigt, wie man nach Typ
; verzweigen kann (Folie 20)
; 
; In dieser Aufgabe brauchen Sie nur die Typen
; Op und Val. Sie können davon ausgehen, dass
; nur mit Ganzahlen gerechnet wird, die Division
; ist also auch immer ganzzahlig.
; ------------------------------------------------

(defn eval-expr [expr] ; nimmt Expression als Parameter entgegen
  (cond
    (instance? Val expr) (.value expr) ; wenn expr vom Typ Val (Zahl) ist, Wert zurückgeben (mit .value)
    (instance? Op expr) ; wenn expr vom Typ Op ist (Operator), dann:
    (let [op (.op expr) ; Operator Wert holen (mit .op)
          left (eval-expr (.left expr)) ; rekursiver Call für linke Seite des Baums
          right (eval-expr (.right expr))] ; rekursiver Call für rechte Seite des Baums
      (case op ; case für alle Operatoren: Operationen definieren
        "+" (+ left right)
        "-" (- left right)
        "*" (* left right)
        "/" (/ left right)))))

; /***** TESTS *******/
(eval-expr expr1) ; 20
(eval-expr expr2) ; 20
(eval-expr expr3) ; 6
(eval-expr expr4) ; 6


; ------------------------------------------------
; TODO: Compilieren Sie eine Expression für unsere
; Stackmaschine aus der letzten Übung
; ------------------------------------------------
(defn compile-expr [expr] ; nimmt Expression als Parameter entgegen
  (cond
    (instance? Val expr) [(str "LOAD " (.value expr))] ; wenn expr vom Typ Val (Zahl) ist, Liste mit LOAD und der Zahl zurückgeben
    (instance? Op expr) ; wenn expr vom Typ Op ist (Operator), dann:
    (let [op (.op expr) ; Operator Wert holen (mit .op)
          code-left (compile-expr (.left expr)) ; rekursiver Call für linke Seite des Baums
          code-right (compile-expr (.right expr)) ; rekursiver Call für rechte Seite des Baums
          op-code (case op ; case für alle Operatoren: Übersetzung definieren
                    "+" "ADD"
                    "-" "SUB"
                    "*" "MUL"
                    "/" "DIV")]
      (concat code-left code-right [op-code])))) ; string für Stackmaschine zusammensetzen

; ------------------------------------------------
; TODO: Testen Sie an einem Ausdruck, ob die
; Stack-Maschine das gleiche Ergebnis liefert
; wie die eval-expr-Funktion
; ------------------------------------------------
(compile-expr expr4)
(run-program (compile-expr expr1))
