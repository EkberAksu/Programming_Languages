%part 3

when(X,Y):-
classes(X,Y,_).

where(X,Y):-
classes(X,_,Y).

enroll(X,Y):-
enrollment(X,Y).

usage(P,T):-
classes(_,T,P).

schedule(S,P,T):-
enroll(S,D),
classes(D,T,P).


meet(X,Y):-
enroll(X,C),
classes(C,T,P),
enroll(Y,C),
classes(C,T,P).

conflict(X,Y):-
(classes(X,T,_),
classes(Y,T,_));
(classes(X,_,P),
classes(Y,_,P)).

%------------------------------------

classes(341,14,z06).
classes(108,12,z11).
classes(102,10,z23).
classes(455,16,207).
classes(452,17,207).

enrollment(e,455).
enrollment(d,341).
enrollment(c,108).
enrollment(b,102).
enrollment(a,102).
enrollment(a,108).



