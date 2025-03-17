% Verwandtschaftsbeziehungen einbinden
:- ['../../SW02/u01_verwandtschaftsbeziehungen'].

:- use_module(library(clpfd)).

% sudoku solver von Folie.
sudoku_local(Rows) :-
    append(Rows, Vs), Vs ins 1..9,
    maplist(all_distinct, Rows),
    transpose(Rows, Columns),
    maplist(all_distinct, Columns),
    Rows = [A,B,C,D,E,F,G,H,I],
    blocks(A, B, C), blocks(D, E, F), blocks(G, H, I),
    maplist(label, Rows).
  
blocks([], [], []).
blocks([A,B,C|Bs1], [D,E,F|Bs2], [G,H,I|Bs3]) :-
    all_distinct([A,B,C,D,E,F,G,H,I]),
    blocks(Bs1, Bs2, Bs3).
  
% Prädikat, um die eine Liste mit 0 zu _ ändern.  
replace_0([], []). % Basisfall: Leere Liste bleibt eine Leere Liste
replace_0([0 | T], [_ | R]) :- replace_0(T, R). % r1: wenn das erste Element eine 0 ist, dann ersetze es mit _. Mache mir dem Tail weiter
replace_0([H | T], [H | R]) :-
    H \= 0, % wenn der HEAD nicht 0 ist.
    replace_0(T, R). % ignoriere es und mache mit dem Tail weiter


relationship(JSONReply, Solution) :-
    % extrahiert FirstPerson, SecondPerson aus dem JSON vom Server. Siehe JSON-Format vom Server
    % die Strings werden atomen umgewandelt.
    atom_string(FirstPerson, JSONReply.firstPerson),
    atom_string(SecondPerson, JSONReply.secondPerson),
    atom_string(Relationship, JSONReply.relationship),
    (call(Relationship, FirstPerson, SecondPerson) -> Solution = "true" ; Solution = "false"). % CONDITION -> print("wahr") ; print("falsch")
    % es wird nach einem Prädikat z.B. parent oder mother gesucht und die beiden Personen mitgegeben. 

sudoku(JSONReply, Solution) :-
    Problem = JSONReply.sudoku,
    maplist(replace_0, Problem, Solution),
    Solution = [A,B,C,D,E,F,G,H,I],
    sudoku_local([A,B,C,D,E,F,G,H,I]).


solve_problem(Problem, Reply, Solution) :-
    call(Problem, Reply, Solution). % call prädikat führt Problem(Reply, Solution) aus.

solve(Problem, Id) :-
    get_problem(Problem, Id, Reply), % hole das Problem anhand des Problems und Id und speichere das JSON in Reply
    solve_problem(Problem, Reply, Solution), % löse das Problem und speichere die Solution
    send_solution(Problem, Id, Solution, _). % sende die Solution an den Server | _ => ignoriere die Response


:- use_module(library(http/http_client)).
:- use_module(library(http/http_json)).

get_problem(Problem, Id, Reply) :- 
    format(string(Url), "http://localhost:16316/problem/~w/~w", [Problem, Id]), % erstelle die URL mit dem gegeben Problem und Id
    http_get(Url, Reply, [json_object(dict)]). % Hole das Problem und speichere das JSON in Reply

send_solution(Problem, Id, Solution, Reply) :-
    string_concat('http://localhost:16316/problem/', Problem, Url), % Erstelle die URL für das spezifische Problem
    http_post(Url, json(json([solution=Solution, id=Id])), Reply, []). % Sende die Lösung als JSON im POST-Request
