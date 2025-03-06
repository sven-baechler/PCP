% Aufgabe a)
:- dynamic fak_as/2.

fak(0, 1). % simple case
fak(N, F) :-
    fak_as(N, F). % if value already asserted
fak(N, F) :-
    N > 0, % argument test
    N1 is N - 1, % evaluate N-1
    fak(N1, F1), % recursive call
    F is N * F1, % sum up
    asserta(fak_as(N, F)), % assert the computed value
    format("Hinweis: Fakultät von ~d wurde gespeichert.~n", [N]).

% Aufgabe b)
fak_clear :-
    retractall(fak_as(_, _)),
    write("Hinweis: Alle gespeicherten Werte wurden gelöscht.").
