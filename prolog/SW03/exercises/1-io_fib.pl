fib_tr(N, F) :- fib_tr(N, 0, 1, F). % call accumulator
fib_tr(0, A, _, A). % simple case
fib_tr(N, A, B, F) :- % general case
    N1 is N - 1, % new argument N1
    N1 >= 0, % avoid underflow
    Sum is A + B, % accumulator Sum
    fib_tr(N1, B, Sum, F). % tail-recursisve call

io_fib :-
    write('Gib eine Zahl ein: '),
    read(N),
    fib_tr(N, F),
    format('Die ~w. Fibonacci-Zahl ist ~w', [N, F]).
