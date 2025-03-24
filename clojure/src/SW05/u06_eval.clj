(ns SW05.u06-eval)

; Wenn Sie nur das Symbol c verwenden dürfen, wie könnten Sie trotzdem das Ergebnis der Berechnung erhalten?
; Tipp: Recherchieren Sie die Funktion eval.
(def a '(* 3 4)) ; a zeigt auf (* 3 4)
(def b 'a) ; b zeigt auf a
(def c 'b) ; c zeigt auf b

; eval wertet eine Form zur Laufzeit aus.
; Hier muss eval verschachtelt verwendet werden, um nur mit c das Ergenbis der Berechnung zu erhalten.
(eval c)
(eval (eval c))
(eval (eval (eval c)))
