% Implementieren Sie die Relation set_difference(Set1, Set2, SetDifference), bei welcher in SetDifference alle Elemente sind, welche in Set1 sind, jedoch nicht in Set2.

% Basisfall: Bei einer leeren Liste gibt es keine Elemente in Set1, also auch keine welche nicht in Set2 sind (Egal welches Set2).
set_difference([], _, []).

% Differenz vorhanden
set_difference([HEAD | TSet1], Set2, [HEAD | TSetDifference]) :- % Set1 und SetDifference in HEAD und TAIL aufsplitten
    \+ member(HEAD, Set2), % Wenn der HEAD von Set1 nicht in Set2 enthalten ist,
    set_difference(TSet1, Set2, TSetDifference). % HEAD von Set1 wird zu SetDifference hinzugefügt (als HEAD). Rekursiver Aufruf mit TAIL von Set1.

% Differenz nicht vorhanden
set_difference([HEAD | TSet1], Set2, SetDifference) :- % Set1 in HEAD und TAIL aufsplitten
    member(HEAD, Set2), % Wenn der HEAD von Set1 in Set2 enthalten ist,
    set_difference(TSet1, Set2, SetDifference). % HEAD von Set1 weglassen und rekursiv mit TAIL von Set1 aufrufen. SetDifference bleibt gleich.

% set_difference([a, b, c, d], [b, d, e, f], [a, c]).
% true.
% set_difference([1, 2, 3, 4, 5, 6], [2, 4, 6], L).
% L = [1, 3, 5].
