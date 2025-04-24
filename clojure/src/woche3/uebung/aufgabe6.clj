(ns woche3.uebung.aufgabe6
  (:require
   [woche3.uebung.parser :refer [parse]]
   [woche3.uebung.aufgabe5 :refer [eval-expr]])
  (:import
   (java.awt BorderLayout Color Dimension)
   (java.awt.event ActionListener)
   (javax.swing JButton JFrame JPanel JTextField)))

(def width 800)
(def height 400)

(defn plot [g func] ; g = Graphics Objekt, func = Funktion
  (let [scale 20 ; Skalierung: 1x entspricht 20 Pixel
        center-x (/ width 2) ; Position am Ursprung
        center-y (/ height 2) ; Position am Ursprung
        env-fn (fn [x] (eval-expr func {"x" x}))] ; Hilfsfunktion um Funktion an der Stelle x zu berechnen.

    (loop [px (- center-x)] ; bei Pixel ganz links beginnen
      (when (< px center-x) ; Wenn noch nicht ganz rechts
        (let [x1 (/ px scale) ; Pixelwert zu mathematischer Einheit umrechnen: 100 Pixel = 5
              y1 (env-fn x1) ; y-Wert berechnen
              x2 (/ (inc px) scale) ; Ein punkt ein Pixel weiter rechts (in mathematischer Einheit)
              y2 (env-fn x2) ; y-Wert berechnen

              ; Mathematische Koordinaten starten vom Ursprung und gehen nach oben/unten und links/rechts
              ; Bildschirmkoordinaten starten oben links und gehen nach rechts/unten 
              sx1 (+ center-x px)
              sy1 (- center-y (* y1 scale))
              sx2 (+ center-x (inc px))
              sy2 (- center-y (* y2 scale))]
          (.drawLine g (int sx1) (int sy1) (int sx2) (int sy2))) ; Linie von sx1/sy1 zu sx2/sy2 zeichnen
        (recur (inc px)))))) ; weitergehen zum nächsten Pixel rechts (px inkrementieren mit inc)


(defn build-plot-panel [func]
  (proxy [JPanel] []
    ; paintComponent überschreiben, um die Funktion zu zeichnen  
    (paintComponent [g]
      (proxy-super paintComponent g)

      ; --------------------------------
      ; zeichne weissen Hintergrund
      (.setColor g Color/WHITE)
      (.fillRect g 0 0 width height)

      ; zeichne x und y-Achse 
      (.setColor g Color/BLACK)
      (.drawLine g 0 (/ height 2) width (/ height 2)) ; x-Achse
      (.drawLine g (/ width 2) 0 (/ width 2) height)  ; y-Achse

      ; TODO plotte die Funktion (siehe plot-Funktion)
      ; --------------------------------
      (.setColor g Color/BLUE)
      (plot g @func))

    (getPreferredSize []
      (Dimension. width height))))

(defn plot-frame
  "Create a JFrame with a plot area and a text field for input."
  []
  (let
   [function (atom (parse "x^2"))          ; Atom für die Funktion
    plot-area (build-plot-panel function)  ; Panel für die Funktion
    text-field (JTextField. "x^2")         ; Textfeld für die Eingabe
    button (JButton. "Plot")               ; Button zum Zeichnen
    input-panel (JPanel.)                  ; Panel für Button und Textfeld

    ; erstelle JFrame
    ; und implementiere ActionListener
    ; für den Button
    frame (proxy [JFrame ActionListener] ["Function plotter"]
            (actionPerformed [e] ; Event-Handling: Wenn der Plot-Button gedrückt wird, dann:
              (let [input (.getText text-field)] ; Input aus Textfeld holen
                (try
                  (let [parsed (parse input)] ; Input parsen und in parsed speichern
                    (reset! function parsed) ; Das function Atom wird mit dem Wert von parsed überschrieben
                    (.repaint plot-area))    ; Funktion neu zeichnen
                  (catch Exception ex
                    (javax.swing.JOptionPane/showMessageDialog ; Fehlermeldung ausgeben (im catch Fall)
                     nil
                     (str "Fehler beim Parsen:\n" (.getMessage ex))
                     "Parser-Fehler"
                     javax.swing.JOptionPane/ERROR_MESSAGE))))))]

    (doto button
      ; Button mit ActionListener verbinden
      (.addActionListener frame)
      (.setFocusable true))

    (doto input-panel
      ; Button und Textfeld in Input-Panel hinzufügen
      (.setLayout (BorderLayout.))
      (.add text-field BorderLayout/CENTER)
      (.add button BorderLayout/EAST))

    (doto frame
      ; JFrame einrichten und anzeigen
      (.setLayout (BorderLayout.))
      (.setResizable false)
      (.add plot-area BorderLayout/CENTER)
      (.add input-panel BorderLayout/SOUTH)
      (.pack)
      (.setVisible true))))

; Fenster anzeigen
(plot-frame)
