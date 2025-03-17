:- use_module(library(clpr)).

% Aufgabe a)
% { DAUGHTER = 15, MOTHER = DAUGHTER * 3, MOTHER + X = 2 * (DAUGHTER + X)}.

% Aufgabe b)
?- use_module(library(clpfd)).

donald_gerald_robert([D,O,N,A,L,D] + [G,E,R,A,L,D] = [R,O,B,E,R,T]) :-
    Vars = [D,O,N,A,L,G,E,R,B,T],
    Vars ins 0..9,
    all_distinct(Vars), % alle Variablen müssen unterschiedlich sein.
    D*100000 + O*10000 + N*1000 + A*100 + L*10 + D +
    G*100000 + E*10000 + R*1000 + A*100 + L*10 + D #=
    R*100000 + O*10000 + B*1000 + E*100 + R*10 + T,
    D #\= 0, G #\= 0, R #\= 0, % Zahlen dürfen nicht mit 0 anfangen
    label(Vars).

% donald_gerald_robert(As + Bs = Cs).
% As = [5, 2, 6, 4, 8, 5],
% Bs = [1, 9, 7, 4, 8, 5],
% Cs = [7, 2, 3, 9, 7, 0] ;
