send_more_money([S,E,N,D] + [M,O,R,E] = [M,O,N,E,Y]) :-
    Vars = [S,E,N,D,M,O,R,Y], % define the variables
    Vars ins 0..9, % define the domain for the vars
    all_distinct(Vars), % all variables must be different
    S*1000 + E*100 + N*10 + D +
    M*1000 + O*100 + R*10 + E #= % attention: use #=/2
    M*10000 + O*1000 + N*100 + E*10 + Y, % addition must be ok
    M #\= 0, S #\= 0, % numbers cannot start with zero
    label(Vars). % assign values to the variables
    