(ns woche3.beispiele-3-map-record-set)

; -------------
; Maps
; -------------


(def letters {:a 1 :b 2 :c 3})

(def scores {"X" 1400
             "Y" 1240
             "Z" 1024})

(def directions {:north 0
                 :east 1
                 :south 2
                 :west 3})

; Zugriff auf Werte
(get letters :a) ; 1 (get als Zugriffsfunktion)
(:a letters)     ; 1 (Keyword als Zugriffsfunktion)
(letters :a)     ; 1 (map als Zugriffsfunktion)

(letters :x 0)   ; 0 (default value)

; neue Schlüssel eintragen, bestehende entfernen (liefert Kopie)
(assoc letters :d 4)   ; {:a 1, :b 2, :c 3, :d 4}
(dissoc letters :b)    ; {:a 1, :c 3}

; Alle Schlüssel, alle Werte
(keys letters)   ; (:a :b :c)
(vals letters)   ; (1 2 3)

; ------------------
; Records
; ------------------


; Song: Titel, Album, Interpreter, Label, Jahr, ...
(defrecord Song [title album interpreter label year])

; Person: Vor-, Nachname, Strasse, PLZ, Ort, Telefon, ...
(defrecord Person [first-name last-name street zip city phone])

; Punkt im Raum mit benannten Komponenten (x, y, z)
(defrecord Point [x y z])

; Konstruktor für Song
(->Song "Yesterday" "Help!" "The Beatles" "Parlophone" 1965)

; Alternative Schreibweise
(Song. "Gangnam Style" "Psy" "Psy" "YG Entertainment" 2012)

; Felder auslesen
(def point2 (Point. 1 2 3))

(:x point2) ; 1
(:y point2) ; 2
(:a point2) ; nil

; oder
(get point2 :y) ; 2

(def point3 (Point. 1 2 3))

; Felder setzen
(def modified-point (assoc point3 :x 10 :y 20))

modified-point ; { :x 10, :y 20, :z 3 }

(defrecord Circle [radius])
(defrecord Rectangle [width height])

(defmulti area type)

(defmethod area Circle [circle]
  (* Math/PI (:radius circle) (:radius circle)))

(defmethod area Rectangle [rect]
  (* (:width rect) (:height rect)))

(def circle (->Circle 5))

(def rect (->Rectangle 4 6))

(area circle) ; 78.53...
(area rect) ; 24

; Verzweigung nach Typ
(defn area2 [shape]
  (cond
    (instance? Circle shape) (* Math/PI (:radius shape) (:radius shape))
    (instance? Rectangle shape) (* (:width shape) (:height shape))
    :else (throw (Exception.
                  "Unknown shape"))))

; Alternative Schreibweise mit condp
(defn area3 [shape]
  (condp = (type shape)
    Circle (* Math/PI (:radius shape) (:radius shape))
    Rectangle (* (:width shape) (:height shape))
    :else (throw (Exception.
                  "Unknown shape"))))

; ------------------
; Sets
; ------------------

#{1 2 3 4}

; #{1 1 2 2} ; Syntax Error, Duplicate key: 1

(set [1 2 2 3]) ; #{1 2 3}


(conj #{1 2} 3) ; #{1 2 3}
(disj #{1 2 3} 2) ; #{1 3}

(contains? #{1 2 3} 2) ; true
(contains? #{1 2 3} 4) ; false

(#{1 2 3} 2) ; 2
(#{1 2 3} 4) ; nil

(require '[clojure.set :as set])

; Vereinigung A u B
(set/union #{1 2} #{2 3}) ; #{1 2 3}

; Schnitt A n B
(set/intersection #{1 2} #{2 3}) ; #{2}

; A ohne B, A \ B, A - B
(set/difference #{1 2} #{2 3}) ; #{1}

