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
% transition(eskalation_ausgeloest, empfaenger_werden_angerufen). % Auskommentiert wegen Stack Overflow
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
path(Start, Ziel, [Start, Ziel]) :- transition(Start, Ziel). % Basisfall der Rekursion: wenn es eine direkte Transition gibt
path(Start, Ziel, [Start | TAIL]) :-
    transition(Start, X), % Zwischenhalt X, der mit Start verbunden ist
    path(X, Ziel, TAIL). % Rekursiver Aufruf mit Zwischenhalt und TAIL der Liste
