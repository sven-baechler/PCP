% Aufgabe 7a)
mult(_, 0, 0). % Multiplikation mit 0 gibt immer 0

% additive Rekursion sieht so aus: A * B = A + (A * (B-1))
% wobei der Teil (A * (B-1)) rekursiv aufgerufen wird

mult(A, B, Result) :-
    B1 is B - 1, % B dekrementieren
    mult(A, B1, R1), % rekursiver Aufruf mit B-1
    Result is R1 + A. % A zum (Zwischen-)Resultat addieren

% Aufgabe 7b)
% Es werden weitere Lösungen gesucht, obwohl es immer nur 1 gibt.
% Durch die Speicherung der Calls hat es keinen Platz mehr im Stack.
