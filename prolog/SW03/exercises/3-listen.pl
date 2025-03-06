% Aufgabe a)
add_tail(X, [], [X]). % simple case
add_tail(X, [HEAD | TAIL], [HEAD | L1]) :- add_tail(X, TAIL, L1). % rekursiver aufruf

% Hier ein Beispiel-Aufruf zur Illustration:
% add_tail(x, [a, b, c], L).

% Aufgabe b)
del([], _, []). % simple case
del([X | TAIL], X, T2) :- del(TAIL, X, T2). % element aus head entfernen
del([X | TAIL], Y, [X | T2]) :- dif(X, Y), del(TAIL, Y, T2). % 

% Hier ein Anwendungsbeispiel:
% del([a, b, c, a, d, a], a, L).

% Aufgabe c)
