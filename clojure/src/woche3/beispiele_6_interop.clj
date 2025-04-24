(ns woche3.beispiele-6-interop)

; ------------------
; Java Interop
; ------------------

; java-Klasse importieren, Beispiel JFrame
(import '[javax.swing JFrame])

; Objekt einer Klasse erzeugen
(def frame (JFrame. "Mein Fenster"))

; Methodenaufrufe
(.setSize frame 300 200)
(.setVisible frame true)

; Methodenaufrufe (alternativ)
(doto frame
  (.setSize 300 200)
  (.setVisible true))

(import [java.awt.event ActionListener])
(import [javax.swing JFrame JButton])

(let [button (JButton. "Klick mich!")
      ; Object erzeugen, welches von JFrame erbt
      ; und ActionListener-Interface implementiert
      frame (proxy [JFrame ActionListener] ["Mein Fenster"]
              (actionPerformed [e]
                (println "Button clicked!")))]

  ; Button mit Listener verbinden
  (.addActionListener button frame)

  ; Fenster mit Button anzeigen
  (doto frame
    (.setSize 300 200)
    (.add button)
    (.setVisible true)))