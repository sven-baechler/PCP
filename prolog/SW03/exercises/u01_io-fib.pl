fib_tr(N, F) :- fib_tr(N, 0, 1, F). % Einstiegspunkt. 0 und 1 sind die ersten 2 Fibonacci Werte --> Akkumulatoren
fib_tr(0, A, _, A). % Basisfall: wenn N = 0, ist der erste Akkumulator true (also 1). Zweiter Akkumulator ist egal
fib_tr(N, A, B, F) :- % Rekursionsfall
    N1 is N - 1, % neues N1 ist N - 1
    N1 >= 0, % sicherstellen, dass N1 nicht kleiner 0 ist
    Sum is A + B, % nächste Fibonacci Zahl imit den beiden Akkumulatoren berechnen
    fib_tr(N1, B, Sum, F). % rekursiver Aufruf (B anstatt A und Sum anstatt B)

io_fib :-
    write('Gib eine Zahl ein: '), % ausgabe
    read(N), % Zahl einlesen
    fib_tr(N, F), % Fibonacci Zahl mit dem input ausrechnen
    format('Die ~w. Fibonacci-Zahl ist ~w', [N, F]). % Lösung ausgeben
