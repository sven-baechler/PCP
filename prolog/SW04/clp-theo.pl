/* Aufgabe: Theo ist 13 Jahre alt. In 3 Jahren ist der
Grossvater doppelt so alt wie Theos Vater und in 7
Jahren ist der Grossvater viermal so alt wie Theo.
Wie alt ist der Vater von Theo?
*/
:- use_module(library(clpr)).

/*
{ T = 13, G+3 = 2*(F+3), G+7 = 4*(T+7) }.

Ausgabe:
T = 13.0, % current age of Theo
G = 73.0, % current age of Theo‘s grandfather
F = 35.0 . % current age of Theo‘s father
*/
