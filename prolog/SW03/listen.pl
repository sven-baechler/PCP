% TODO liste instanzieren
%liste = [a, b, c].

% membership in listen
mem(X, [X | _]). % tail doesn’t matter
mem(X, [_| Tail]) :- mem(X, Tail). % head doesn’t matter

% mem(a, [a, b, c]).

% Permutationen
% L = [_, _, _], mem(a, L), mem(b, L), mem(c, L).

conc([], L, L).
conc([X | L1], L2, [X | L3]) :- conc(L1, L2, L3).