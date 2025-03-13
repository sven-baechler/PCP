% Lösung mit (green) Cut
warn(T) :- T < 80, write('Temperatur ok'), !.
warn(T) :- T < 100, write('Temperatur sehr warm'), !.
warn(_) :- write('Temperatur zu heiss').

% Lösung ohne Cut (liefert "falsche" Ergebnisse)
warn2(T) :- T < 80, write('Temperatur ok').
warn2(T) :- T < 100, write('Temperatur sehr warm').
warn2(_) :- write('Temperatur zu heiss').

% Lösung ohne Cut (korrigiert)
warn3(T) :- T < 80, write('Temperatur ok').
warn3(T) :- T >= 80, T < 100, write('Temperatur sehr warm').
warn3(T) :- T >= 100, write('Temperatur zu heiss').

% ohne Cut (warn2/1) werden z.B. bei input 50 alle drei Lösungen ausgegeben
% warn3/1 musste noch angepasst werden, damit nur eine Lösung ausgegeben wird
