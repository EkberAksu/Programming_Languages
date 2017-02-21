flatten(L, FlatL) :-
	flatten(L, [], FlatL).
 
flatten(X, T, [X|T]) :-
	X(X), !.
flatten([], T, T) :- !.
flatten([Head|T], TailL, L) :- !,
	flatten(Head, FlatTail, L),
	flatten(T, TailL, FlatTail).
 
flatten(NonL, T, [NonL|T]).