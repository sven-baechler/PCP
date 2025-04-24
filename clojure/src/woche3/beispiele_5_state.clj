(ns woche3.beispiele-5-state)

; ------------------
; mutable state
; ------------------


(def my-atom (atom 42)) ; Ein Atom mit Startwert 42

(def my-ref (ref 42)) ; Ein Ref mit Startwert 42

@my-atom         ; 42
(deref my-atom)  ; 42

(def counter (atom 0))
(swap! counter inc) ; atomare Veränderung

@counter ; gibt 1 zurück

; atomare Liste
(def elems (atom ()))

; Elemente in die Liste einfügen
(swap! elems conj 1)

; noch eins
(swap! elems conj 2)

(deref elems) ; gibt (2 1)


(def konto-a (ref 100))
(def konto-b (ref 50))

(dosync
 (alter konto-a - 10)
 (alter konto-b + 10))

@konto-a ; 90
@konto-b ; 60

(import [java.util HashMap])

; Konten von Alice und Bob
(def accounts (HashMap. {"Alice" 1000 "Bob" 1000}))

(defn transfer [from to amount]
  (let [current (.get accounts from)]
    (when (>= current amount)
      (.put accounts from (- current amount))
      (.put accounts to (+ (.get accounts to) amount)))))

(defn simulate-transfers []
  (dotimes [_ 100]
    (future (transfer "Alice" "Bob" 10))
    (future (transfer "Bob" "Alice" 10))))

; Simulation
(simulate-transfers)
accounts



(def alice-balance (ref 1000))

(def bob-balance (ref 1000))

(defn transfer-ref [from to amount]
  (dosync
   (let [current @from]
     (when (>= current amount)
       (alter from - amount)
       (alter to + amount)))))

(defn simulate-transfers-ref []
  (dotimes [_ 100]
    (future (transfer-ref alice-balance bob-balance 10))
    (future (transfer-ref bob-balance alice-balance 10))))

; Simulation
(simulate-transfers-ref)
[@alice-balance @bob-balance]