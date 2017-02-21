flight(istanbul,ankara).
flight(ankara,istanbul).

flight(istanbul,trabzon).
flight(trabzon,istanbul).

flight(istanbul,kars).
flight(kars,istanbul).

flight(istanbul,gaziantep).
flight(gaziantep,istanbul).

flight(istanbul,konya).
flight(konya,istanbul).

flight(istanbul,antalya).
flight(antalya,istanbul).

flight(istanbul,izmir).
flight(izmir,istanbul).

flight(ankara,trabzon).
flight(trabzon,ankara).

flight(ankara,kars).
flight(kars,ankara).

flight(ankara,konya).
flight(konya,ankara).

flight(ankara,izmir).
flight(izmir,ankara).

flight(edirne,edremit).
flight(edremit,edirne).

flight(erzincan,edremit).
flight(edremit,erzincan).

distance(istanbul,ankara,350).
distance(ankara,istanbul,350).

distance(istanbul,trabzon,902).
distance(trabzon,istanbul,902).

distance(istanbul,kars,1189).
distance(kars,istanbul,1189).

distance(istanbul,gaziantep,848).
distance(gaziantep,istanbul,848).

distance(istanbul,konya,461).
distance(konya,istanbul,461).

distance(istanbul,antalya,482).
distance(antalya,istanbul,482).

distance(istanbul,izmir,328).
distance(izmir,istanbul,328).

distance(ankara,trabzon,578).
distance(trabzon,ankara,578).

distance(ankara,kars,872).
distance(kars,ankara,872).

distance(ankara,konya,231).
distance(konya,ankara,231).

distance(ankara,izmir,521).
distance(izmir,ankara,521).

distance(edirne,edremit,168).
distance(edremit,edirne,168).

distance(erzincan,edremit,1044).
distance(edremit,erzincan,1044).

route(X,Y,List,D):- distance(X,Y,D),\+(member(Y,List)). % \+ eqals 'not', it compiles on gprolog
 
 route(X,Y,D) :-  distance(X,Y,D).
 route(X,Y,D) :-  distance(X,Z,D),route(Z,Y,[X,Z],D1).
 route(X,Y,D) :-  distance(X,Z,D),route(Z,M,[X,Z],D1),D is D+ D1, route(M,Y,[X,Z,M],D2),D is D+ D2.
 
 findMin([Only], Only).
 findMin([Head|Tail], Minimum) :-
    findMin(Tail, TailMin),
    Minimum is min(Head, TailMin). 
 
sroute(X,Y,D) :-
			findall(L,route(X,Y,L),Z),
			findMin(Z,D).
			
	
	
	