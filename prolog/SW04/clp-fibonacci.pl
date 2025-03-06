:- use_module(library(clpr)).
fib_clp(N, F) :- { N = 0, F = 0 }.
fib_clp(N, F) :- { N = 1, F = 1 }.
fib_clp(N, F) :-
    { N >= 2, F = F1+F2, N1 = N-1, N2 = N-2 },
    fib_clp(N1, F1),
    fib_clp(N2, F2).

/*
fib_clp(6, F).
--> F = 8.0

fib_clp(N, 8).
--> N = 6.0 .
*/
