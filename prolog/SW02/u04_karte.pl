% Farbkombinationen von Nachbarskantonen
n(red, green).
n(red, yellow).
n(green, red).
n(green, yellow).
n(yellow, red).
n(yellow, green).

% Alle Kantone
colors(LU, NW, OW, SZ, UR, ZG) :-
UR = yellow, % Uri ist Gelb
SZ = red, % Schwyz ist rot
n(UR, OW), n(UR, NW), n(UR, SZ), % Nachbarkantone von Uri
n(NW, OW), n(NW, LU), n(NW, SZ), % Nachbarkantone von Nidwalden
n(OW, LU), % Nachbarkantone von Obwalden
n(LU, SZ), n(LU, ZG), % Nachbarkantone von Luzern
n(ZG, SZ). % Nachbarkantone von Zug
% Nachbarkantone von Schwyz wurden schon alle erwähnt

% Es gibt nur eine mögliche Lösung:
% colors(LU, NW, OW, SZ, UR, ZG).
% LU = UR, UR = yellow,
% NW = ZG, ZG = green,
% OW = SZ, SZ = red ;
