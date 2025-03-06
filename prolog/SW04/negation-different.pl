different(X, X) :- !, fail.
different(_X, _Y). % anonymous variables to avoid compiler warning

/*
different(X, tom).
--> false.
?- different(tom, 3).
--> true.
?- different(1 + 2, 3).
--> true.
?- different(tom, mary).
--> true.
*/
