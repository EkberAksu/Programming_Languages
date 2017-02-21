
unique([],[]).
unique([Head|Tail],CH) :- isMem(Head,Tail),!, unique(Tail,CH).
unique([Head|Tail],[Head|CH]) :- unique(Tail,CH).

isMem(X,[Head|_]) :- X==Head,!.
isMem(X,[_|Tail]) :- isMem(X,Tail).