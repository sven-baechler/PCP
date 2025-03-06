% Aufgabe 6a)
% Wie wird in SWI-Prolog der Term X is 16 / 4 / 2 ausgewertet? Erklären sie, weshalb das so ist.

% X is 16 / 4 / 2.
% X = 2.

% Der Operator / ist in Prolog linksassoziativ, also wird der Term von links nach rechts ausgerechnet:
% (16 / 4) / 2
% (16 / 4) = 4
% 4 / 2 = 2
% X = 2.

% Aufgabe 6b)
% Was ist die Antwort auf die Anfrage Y = 3, X = Y - 1. ?

% Y = 3,
% X = Y - 1.

% X wird an Y gebunden. X wird nicht ausgerechnet, sondern als Term gespeichert.

% Aufgabe 6c)
% Was ist die Antwort auf die Anfrage Y = 3, X is Y - 1. ? Wie lässt sich das unterschiedliche Resultat gegenüber der Teilaufgabe b) erklären?

% Y = 3,
% X = 2.

% Der is Operator rechnet den Ausdruck aus.
