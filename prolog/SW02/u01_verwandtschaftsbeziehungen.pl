% all females
female(mary).
female(liz).
female(mia).
female(tina).
female(ann).
female(sue).

% all males
male(mike).
male(jack).
male(fred).
male(tom).
male(joe).
male(jim).

% all childern of mary
parent(mary, mia).
parent(mary, fred).
parent(mary, tina).

% all children of mike
parent(mike, mia).
parent(mike, fred).
parent(mike, tina).

% all childern of liz
parent(liz, tom).
parent(liz, joe).

% all childern of jack
parent(jack, tom).
parent(jack, joe). 

% all childern of mia
parent(mia, ann).

% all childern of tina
parent(tina, sue).
parent(tina, jim).

% all childern of tom
parent(tom, sue).
parent(tom, jim).

% Aufgabe 1a)
mother(Mother, Child) :-
    female(Mother),
    parent(Mother, Child).

father(Father, Child) :-
    male(Father),
    parent(Father, Child).

% Verwenden Sie dieses beiden Prädikate in der Folge beispielsweise, um zu überprüfen, wer die Mutter und der Vater von jim ist.
% parent(X, jim).

% Und mit welcher Abfrage können Sie herausfinden, wie alle Kinder von Mary heissen?
% parent(mary, X).

% Aufgabe 1b)
sibling(X, Child) :-
    parent(Y, X),
    parent(Y, Child).

% Aufgabe 1c)
grandmother(GrandMother, GrandChild) :-
    mother(GrandMother, Parent),
    parent(Parent, GrandChild).

% Lassen Sie sich mit diesem Prädikat alle Grossmütter von jim ausgeben. Wie lautet die entsprechende Anfrage?
% grandmother(X, jim).

% Aufgabe 1d)
offspring(Young, Old) :- parent(Old, Young).
offspring(Young, Old) :- parent(Parent, Young), offspring(Parent, Old).
