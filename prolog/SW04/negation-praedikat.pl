snake(snake).
animal(snake).
animal(horse).
animal(dog).
likes(mary, X) :- snake(X), !, fail.
likes(mary, X) :- animal(X).

% die korrekte syntax lautet wie folgt: \+
likes_with_negation(mary, X) :- animal(X), \+ snake(X).
different_with_negation(X, Y) :- \+ X = Y.
