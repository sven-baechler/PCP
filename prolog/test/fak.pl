fak(0, 1). % simple case
fak(N, F) :- % general case
N > 0, % argument test
N1 is N - 1, % evaluate N-1
fak(N1, F1), % recursive call
F is N * F1. % sum up 