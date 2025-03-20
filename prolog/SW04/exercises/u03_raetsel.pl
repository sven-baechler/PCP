:- use_module(library(clpr)).

% Aufgabe a: "Die Tochter ist 15 Jahre alt, die Mutter dreimal so alt. In wie vielen Jahren wird die Mutter nur noch doppelt so alt sein wie ihre Tochter?"
% { DAUGHTER = 15, MOTHER = DAUGHTER * 3, MOTHER + X = 2 * (DAUGHTER + X) }.

% Output:
% DAUGHTER = X, X = 15.0,
% MOTHER = 45.0.

% Aufgabe b)
?- use_module(library(clpfd)).

donald_gerald_robert([D,O,N,A,L,D] + [G,E,R,A,L,D] = [R,O,B,E,R,T]) :-
    Vars = [D,O,N,A,L,G,E,R,B,T], % Variablen definieren
    Vars ins 0..9, % Zahlenbereich für Variablen definieren
    all_distinct(Vars), % alle Variablen müssen unterschiedlich sein.
    D * 100000 + O * 10000 + N * 1000 + A * 100 + L * 10 + D + % DONALD
    G * 100000 + E * 10000 + R * 1000 + A * 100 + L * 10 + D #= % plus GERALD
    R * 100000 + O * 10000 + B * 1000 + E * 100 + R * 10 + T, % gleich ROBERT
    D #\= 0, G #\= 0, R #\= 0, % Zahlen dürfen nicht mit 0 anfangen
    label(Vars). % den Variablen Werten zuweisen, welche die Bedingungen erfüllen

% donald_gerald_robert(As + Bs = Cs).
% As = [5, 2, 6, 4, 8, 5],
% Bs = [1, 9, 7, 4, 8, 5],
% Cs = [7, 2, 3, 9, 7, 0] ;
