(ns SW05.u03-fehler)

; Notiz: mit (type X) wird der Datentyp von X ausgegeben

(+ (* 2 3) (/ 3 4))
; 27/4 --> clojure.lang.Ratio

;(+ - * /)
; Fehler --> nach dem Plus müsste eine Zahl stehen, darum "cannot be cast to class java.lang.Number"

'(+ - * /)
; wegen dem Hochkomma gibt es die Liste zurück --> clojure.lang.PersistentList

(str 1.0 2 true)
; str fügt alle Argumente als strings zusammen --> java.lang.String

(+ (parse-long "123") (parse-double "0.45"))
; parst die strings zu Zahlen und addiert sie
; 123.45 --> java.lang.Double

(or "this" "is" "crazy")
; gibt den ersten true Wert zurück, also "this". Alles bis auf false und nil ist true
; "this" --> java.lang.String

(and "this" "is" "crazy")
; gibt den letzten Wert zurück, wenn alle true sind, also "crazy".
; "crazy" --> java.lang.String

; (not "this" "is" "crazy")
; Wrong number of args (3) passed to: clojure.core/not --> not erwartet 1 Argument

((fn [x] (/ 1 x)) (/ 5 2))
; die anonyme Funktion fn wird mit 5/2 aufgerufen
; 2/5 --> clojure.lang.Ratio

(int? 3000000000)
; prüft, ob der Parameter eine Ganzzahl ist (warum geht das mit so grossen Zahlen?)
; true --> java.lang.Boolean
