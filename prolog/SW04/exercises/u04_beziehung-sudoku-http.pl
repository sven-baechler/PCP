% Problem-Solver starten
% java -jar pcp-problem-provider21.jar

% Verwandtschaftsbeziehungen einbinden
:- ['../../SW02/u01_verwandtschaftsbeziehungen'].

% Module einbinden
:- use_module(library(clpfd)).
:- use_module(library(http/http_client)).
:- use_module(library(http/http_json)).

% Prädikat zum Lösen von Problemen (Ausgangspunkt)
solve(Problem, Id) :-
    get_problem(Problem, Id, Reply), % Holt das Problem vom Problem-Provider anhand dem ProblemTyp und der ProblemId. Antwort wird in Reply gespeichert.
    solve_problem(Problem, Reply, Solution), % Löst das Problem und speichert die Lösung in Solution
    send_solution(Problem, Id, Solution, _). % Sendet die Lösung zurück an den Problem-Provider und ignoriert die Response

% Problem von Problem-Provider holen
get_problem(Problem, Id, Reply) :- 
    format(string(Url), "http://localhost:16316/problem/~w/~w", [Problem, Id]), % URL zusammenstellen (ProblemTyp und ProblemId)
    http_get(Url, Reply, [json_object(dict)]). % Macht einen GET-Request auf die URL und speichert die Antwort in Reply als Prolog Dictionary

% Problem Lösen (generisches Prädikat)
solve_problem(Problem, Reply, Solution) :-
    call(Problem, Reply, Solution). % call prädikat führt Problem(Reply, Solution) aus, also entweder relationship(Reply, Solution) oder sudoku(Reply, Solution).

% Lösung zurück an den Problem-Provider senden
send_solution(Problem, Id, Solution, Reply) :-
    format(string(Url), "http://localhost:16316/problem/~w", [Problem]), % URL zusammenstellen (nur ProblemTyp)
    http_post(Url, json(json([solution=Solution, id=Id])), Reply, []). % Macht einen POST-Request auf die URL mit der Solution als JSON im Body

% Relationship Problem lösen
relationship(JSONReply, Solution) :-
    % atom_string speichert die Dictionary-Werte aus der JSON Reply in Atomen (FirstPerson, SecondPerson, Relationship).
    atom_string(FirstPerson, JSONReply.firstPerson),
    atom_string(SecondPerson, JSONReply.secondPerson),
    atom_string(Relationship, JSONReply.relationship),
    % call ruft das Prädikat parent/2, mother/2, father/2, sibling/2, grandmother/2 oder offspring/2 auf (aus SW02/u01_verwandtschaftsbeziehungen).
    % call(Prädikat, 1. Argument, 2. Argument)
    % CONDITION -> TRUE ; FALSE
    (call(Relationship, FirstPerson, SecondPerson) -> Solution = "true" ; Solution = "false").

% das Prädikat grandfather/2 musste noch ergänzt werden
% Predicate father/2 und parent/2 not defined Fehlermeldung kann ignoriert werden, werden aus SW02/u01_verwandtschaftsbeziehungen eingebunden.
grandfather(GrandFather, GrandChild) :-
    father(GrandFather, Parent),
    parent(Parent, GrandChild).

% Sudoku Problem lösen
sudoku(JSONReply, Solution) :-
    Problem = JSONReply.sudoku, % Speichert die Sudoku-Matrix aus der JSON Reply in Problem
    maplist(replace_0, Problem, Solution), % alle 0 (leere Felder) durch _ ersetzen und in Solution speichern
    Solution = [A,B,C,D,E,F,G,H,I], % die Matrix (Solution) in 9 Listen aufteilen
    sudoku_local([A,B,C,D,E,F,G,H,I]). % die 9 Listen mit sudoku_local lösen

% Prädikat, um die eine Liste mit 0 zu _ ändern.
replace_0([], []). % Basisfall: leere Liste bleibt eine Leere Liste
replace_0([0 | TAIL], [_ | R]) :- replace_0(TAIL, R). % wenn der HEAD 0 ist, wird es mit _ ersetzt. Rekursiver Aufruf mit dem TAIL
replace_0([HEAD | TAIL], [HEAD | R]) :-
    HEAD \= 0, % wenn der HEAD nicht 0 ist
    replace_0(TAIL, R). % HEAD ignorieren, rekursiver Aufruf mit dem TAIL

% Sudoku Programm aus den Folien.
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
