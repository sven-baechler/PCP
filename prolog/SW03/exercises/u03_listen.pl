% Aufgabe a)
% add_tail(X, L, L1) --> Element X am Ende der Liste L anfügen, damit L1 entsteht.
add_tail(X, [], [X]). % Basisfall: einer leeren Liste das Element X anhängen = Liste mit Element X
add_tail(X, [HEAD | TAIL], [HEAD | L1]) :- add_tail(X, TAIL, L1). % rekursiver Aufruf
% L in HEAD und TAIL splitten, HEAD in L1 übernehmen, rekursiver Aufruf ohne HEAD bis TAIL leer ist, X anhängen (Basisfall)

% Hier ein Beispiel-Aufruf zur Illustration:
% add_tail(x, [a, b, c], L).
% L = [a, b, c, x]

% Aufgabe b)
% del(L, X, L1) --> alle X aus L löschen, damit L1 entsteht.
del([], _, []). % Basisfall: leere Liste = leere Liste, egal was entfernt werden soll
del([X | TAIL], X, L1) :- del(TAIL, X, L1), !. % wenn der head das zu entfernende Element X ist, weglassen und rekursiv mit TAIL aufrufen
% cut operator, damit nicht weitere lösungen gefunden werden
del([HEAD | TAIL], X, [HEAD | L1]) :- del(TAIL, X, L1). % wenn HEAD nicht das zu entfernende Element X ist, rekursiv mit TAIL aufrufen

% Hier ein Anwendungsbeispiel:
% del([a, b, c, a, d, a], a, L).

% Aufgabe c)
mem_d(X, L) :- del(L, X, L1), L \= L1.
% Element aus Liste entfernen, wenn die Liste nicht mehr gleich war wie vorher das Element drin

% mem_d(5, [1,2,3,4,5]).
% true

% Aufgabe d)
%  rev_acc(L, A, R) --> R ist L in umgekehrter Reihenfolge. A ist der Akkumulator
rev_acc([], A, A). % Basisfall: leere Liste heisst Akkumulator = Resultat
rev_acc([HEAD | TAIL], A, R) :- rev_acc(TAIL, [HEAD | A], R). % HEAD wird von Liste in Akkumulator geladen. weil es wie ein stack funktioniert ist die liste am schluss umgekehrt

% rev_acc([a, b, c, d], [], L).
% L = [d, c, b, a].

% Aufgabe e)
rev(L, R) :- rev_acc(L, [], R). % rev_acc mit leerem Akkumulator aufrufen (wie eine Überladung der Methode)

% rev([a, b, c, d], L).
% L = [d, c, b, a].
