% Aufgabe 6)
% Permutationen erzeugen
% L = [_, _, _], member(a, L), member(b, L), member(c, L).

% Aufgabe a)
% Cut einbauen, damit nur 1 Permutation erzeugt wird
% L = [_, _, _], member(a, L), !, member(b, L), !, member(c, L).
% der Cut bindet das a an 1. Stelle, das b an 2. Stelle und das c schlussendlich an 3. Stelle. Somit nur diese eine Lösung.

% L = [_, _, _], member(a, L), member(b, L), member(c, L), !.
% Optimierung

% Aufgabe b)
% Ein Red Cut verändert die deklarative Bedeutung eines Prädikats = andere Lösung(en)
