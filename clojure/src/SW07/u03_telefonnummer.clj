; Gegeben ist ein globales Verzeichnis mit Vornamen und Telefonnummer von Personen:
(def my-phone-dir
  (atom
   (list (hash-map :name 'Adam :phone 4711)
         (hash-map :name 'Eva :phone 4712))))

; Ein Programm soll zwei Möglichkeiten bieten:
; 1. Suchen der Nummer anhand des Vornamens
; 2. Hinzufügen einer neuen Person mittels Vornamen und Nummer im Verzeichnis


; a) Warum widerspricht diese Interaktion fundamental unserem bisherigen funktionalen Programmiergrundsatz?
; Wir möchten in der funktionalen Programmierung immutable Daten. add-entry verändert jedoch den Inhalt des my-phone-dir Atoms.

; b) Schreiben Sie die Funktion look-at für das Suchen der Nummer anhand des Vornamens
(defn look-at [phone-dir name]
  (some #(when (= (:name %) name) (:phone %)) @phone-dir))
; @ gibt den Wert des Atoms
; some gibt das erste "wahre" Ergebnis zurück (welches die Bedingung erfüllt)
; % ist das Element der aktuellen Iteration
; (:name %) gibt den Namen aus der map (Atom) zurück
; der Name aus dem Atom wird mit dem Namen aus dem Funktionsaufruf (name) verglichen
; wenn er übereinstimmt, wird die Telefonnummer aus dem Atom zurückgegeben (:phone %)

; c) Schreiben Sie die Funktion add-entry für das Hinzufügen einer neuen Person mit Vornamen und Nummer.
(defn add-entry [phone-dir name number]
  (swap! phone-dir conj {:name name :phone number}))
; swap! verändert das Atom: Atom und neuer Wert mitgeben
; conj hängt der Liste ein neues Element an (Hinweis: swap! übergibt dem conj automatisch das Atom als erstes Argument)
; {:name name :phone number} neuer Entry als map

; Eine mögliche Interaktion könnte dann wie folgt verlaufen:
(look-at my-phone-dir 'Adam) ; 4711
(look-at my-phone-dir 'Eva) ; 4712
(look-at my-phone-dir 'Alex) ; nil
(add-entry my-phone-dir 'Alex 4713) ; nil
(look-at my-phone-dir 'Alex) ; 4713
