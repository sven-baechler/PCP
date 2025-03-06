% ohne clp
convert(Celsius, Fahrenheit) :-
    Celsius is (Fahrenheit - 32)*5/9.

/*
convert(C, 95).
--> C = 35.

?- convert(35, F).
--> ERROR: is/2: Arguments are not sufficiently instantiated
*/

% mit clp
:- use_module(library(clpr)).
convert_clp(Celsius, Fahrenheit) :-
{ Celsius = (Fahrenheit - 32) * 5 / 9 }.

/*
convert_clp(35, F).
--> F = 95.0 .

convert_clp(C, F).
--> {F=32.0+1.7999999999999998*C}.
*/
