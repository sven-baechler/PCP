% Aufgabe a)
:- dynamic fak_as/2. % fak_as/2 als dynamisches Prädikat deklarieren (so können neue Fakten zur Laufzeit gespeichert werden)

fak(0, 1). % Basisfall: Fakultät von 0 ist 1 (per Definition)
fak(N, F) :-
    fak_as(N, F), % überprüfen, ob der Wert bereits in fak_as/2 gespeichert wurde
    format('(Hinweis: Fakulät von ~w war gespeichert.)', [N]), % Ausgabe
    !. % ein Green Cut verhindert, dass weitere Ergebnisse gespeichert werden
fak(N, F) :-
    N > 0, % sicherstellen, dass N grösser 0 ist
    N1 is N - 1, % neues N1 ist N - 1
    fak(N1, F1), % rekursiver Aufruf mit N1
    F is N * F1, % Fakultät berechnen
    asserta(fak_as(N, F)). % Fakt speichern

% Aufgabe b)
fak_clear :-
    retractall(fak_as(_, _)), % alle zur Laufzeit gespeicherten Fakten löschen
    write('(Hinweis: Alle gespeicherten Werte wurden gelöscht.)'). % Ausgabe
