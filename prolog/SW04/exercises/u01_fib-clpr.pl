% CLPR using
:- use_module(library(clpr)).

% Fibonacci mit CLP-R aus den Folien
fib_clp(N, F) :- { N = 0, F = 0 }.
fib_clp(N, F) :- { N = 1, F = 1 }.
fib_clp(N, F) :-
    {
        N >= 2,
        F = F1 + F2,
        N1 = N - 1,
        N2 = N - 2
    },
    fib_clp(N1, F1),
    fib_clp(N2, F2).

% Aufgabe a: Wie manifestiert sich dieses Problem in SWI-Prolog?
% fib_clp(N, 8).
% N = 6.0; --> weitere Lösungen suchen (Space)
% Stack limit (1.0Gb) exceeded

% Aufgabe b: Wieso tritt dieses Problem auf?
% Weil versucht wird eine weitere Lösung zu finden. Es gibt jedoch nur eine Lösung. Es wird immer weiter gesucht bis der Stack voll ist.
% CLP versucht die Gleichung zu lösen

% Aufgabe c: Lässt sich das oben beschriebene Problem einfach beheben?
fib_clp_new(N, F) :- { N = 0, F = 0 }.
fib_clp_new(N, F) :- { N = 1, F = 1 }.
fib_clp_new(N, F) :-
    {
        N >= 2,
        F + 1 >= N, % Zusätzliche Regel: N (N-te Fibonacci Zahl) darf nicht grösser sein als die Lösung (F)
        F = F1 + F2,
        N1 = N - 1,
        N2 = N - 2
    },
    fib_clp_new(N1, F1),
    fib_clp_new(N2, F2).

% fib_clp(N, 8).
% X = 6.0; --> weitere Lösungen suchen (Space)
% false.

% Hinweis: ab 26 ist die Zahl zu gross und es gibt trotzdem ein Stack Overflow
