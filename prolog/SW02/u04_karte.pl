% Farbkombinationen von Nachbarskantonen
n(red, green).
n(red, yellow).
n(green, red).
n(green, yellow).
n(yellow, red).
n(yellow, green).

% Alle Kantone
colors(Lu, Nw, Ow, Sz, Ur, Zg) :-
Ur = yellow, % Uri ist Gelb
Sz = red, % Schwyz ist rot
n(Ur, Ow), n(Ur, Nw), n(Ur, Sz), % Nachbarkantone von Uri
n(Nw, Ow), n(Nw, Lu), n(Nw, Sz), % Nachbarkantone von Nidwalden
n(Ow, Lu), % Nachbarkantone von Obwalden
n(Lu, Sz), n(Lu, Zg), % Nachbarkantone von Luzern
n(Zg, Sz). % Nachbarkantone von Zug
% Nachbarkantone von Schwyz wurden schon alle erwähnt

% Es gibt nur eine mögliche Lösung:
% colors(Lu, Nw, Ow, Sz, Ur, Zg).
% Lu = Ur, Ur = yellow,
% Nw = Zg, Zg = green,
% Ow = Sz, Sz = red ;
