snake(snake).
animal(snake).
animal(horse).
animal(dog).
likes(mary, X) :- snake(X), !, fail.
likes(mary, X) :- animal(X).

/*
likes(mary, horse).
--> true.
likes(mary, snake).
--> false.
*/
