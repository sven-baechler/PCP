% Aufgabe a)
add_tail(X, [], [X]). % Basisfall: einer leeren liste das element X anhängen = liste mit element X
add_tail(X, [HEAD | TAIL], [HEAD | L1]) :- add_tail(X, TAIL, L1). % rekursiver aufruf

% Hier ein Beispiel-Aufruf zur Illustration:
% add_tail(x, [a, b, c], L).

% Aufgabe b)
del([], _, []). % Basisfall: leere liste = leere liste, egal was entfernt werden soll
del([X | TAIL], X, L1) :- del(TAIL, X, L1). % wenn head das zu entfernende element ist, weglassen und rekursiv aufrufen
del([HEAD | TAIL], X, [HEAD | L1]) :- del(TAIL, X, L1). % wenn head nicht das zu entfernende element ist, aufrufen

% Hier ein Anwendungsbeispiel:
% del([a, b, c, a, d, a], a, L).

% Aufgabe c)
mem_d(X, L) :- del(L, X, L1), L \= L1. % Element aus Liste entfernen, wenn Liste nicht mehr gleich war das Element drin

% mem_d(5, [1,2,3,4,5]).
% true

% Aufgabe d)
rev_acc([], A, A). % Basisfall: leere Liste heisst Akkumulator = Resultat
rev_acc([HEAD | TAIL], A, R) :- rev_acc(TAIL, [HEAD | A], R). % Head wird von Liste in Akkumulator geladen. weil es wie ein stack funktioniert ist die liste am schluss umgekehrt

% rev_acc([a, b, c, d], [], L).

% Aufgabe e)
rev(L, R) :- rev_acc(L, [], R).

% rev([a, b, c, d], L).
