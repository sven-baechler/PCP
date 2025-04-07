% States definieren
state(warten).
state(ausloeser_wird_angerufen).
state(warten_auf_bestaetigung).
state(empfaenger_werden_angerufen).
state(eskalation_ausgeloest).
state(empfaenger_in_anruf).
state(empfaenger_nicht_erreichbar).
state(konferenz_aktiv).
state(benutzerdefinierter_ansagetext).
state(systemdefinierter_ansagetext).
state(weniger_als_zwei_aktive_teilnehmer).
state(endzustand).

% Transitions definieren
transition(warten, warten_auf_bestaetigung).
transition(warten, empfaenger_werden_angerufen).
transition(warten, ausloeser_wird_angerufen).
transition(ausloeser_wird_angerufen, warten_auf_bestaetigung).
transition(ausloeser_wird_angerufen, endzustand).
transition(warten_auf_bestaetigung, empfaenger_werden_angerufen).
transition(warten_auf_bestaetigung, endzustand).
transition(eskalation_ausgeloest, empfaenger_werden_angerufen).
transition(empfaenger_werden_angerufen, empfaenger_in_anruf).
transition(empfaenger_werden_angerufen, empfaenger_nicht_erreichbar).
transition(empfaenger_in_anruf, weniger_als_zwei_aktive_teilnehmer).
transition(empfaenger_in_anruf, konferenz_aktiv).
transition(empfaenger_in_anruf, empfaenger_nicht_erreichbar).
transition(konferenz_aktiv, weniger_als_zwei_aktive_teilnehmer).
transition(weniger_als_zwei_aktive_teilnehmer, endzustand).
transition(empfaenger_nicht_erreichbar, eskalation_ausgeloest).
transition(empfaenger_nicht_erreichbar, benutzerdefinierter_ansagetext).
transition(empfaenger_nicht_erreichbar, systemdefinierter_ansagetext).
transition(benutzerdefinierter_ansagetext, endzustand).
transition(systemdefinierter_ansagetext, endzustand).

% Prädikat, um Pfade zu suchen
path(Start, Ziel, [Start, Ziel], History) :- % Basisfall
    transition(Start, Ziel),
    check_eskalation(Ziel, History). % Sicherstellen, dass Eskalation nicht ein zweites mal durchlaufen wird

path(Start, Ziel, [Start | Tail], History) :- % Rekursiver Fall
    transition(Start, Next),
    check_eskalation(Next, History), % Sicherstellen, dass Eskalation nicht ein zweites mal durchlaufen wird
    path(Next, Ziel, Tail, [Next | History]).

path(Start, Ziel, Pfad) :- % Zum Aufrufen ohne Liste
    path(Start, Ziel, Pfad, [Start]).

check_eskalation(eskalation_ausgeloest, History) :- % wenn der nächste State Eskalation ist
    \+ member(eskalation_ausgeloest, History), !. % darf er nicht bereits in der History stehen. Wenn doch, Red Cut.

check_eskalation(State, _) :- % wenn der nächste State nicht Eskalation ist, weitermachen.
    State \= eskalation_ausgeloest.

% Flag setzen, damit die ganze Liste in der Ausgabe angezeigt wird
% set_prolog_flag(answer_write_options, [max_depth(0)]).

% Aufrufen: Resultat sind alle möglichen Pfade.
% path(warten, endzustand, Path).
