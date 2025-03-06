:- dynamic fib_as/2.

fib_as(0, 0). % base case 1
fib_as(1, 1). % base case 2
fib_as(N, F) :- % general rule
    N > 1, % allow no negative numbers
    N1 is N - 1,
    N2 is N - 2,
    fib_as(N1, F1), % calculate F1 = fib(N-1)
    fib_as(N2, F2), % calculate F2 = fib(N-2)
    F is F1 + F2,
    asserta(fib_as(N, F)). % assert new fact

% fib_as(100, X).
